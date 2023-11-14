package com.address.book.service.impl;
import com.address.book.domain.response.ContactDTO;
import com.address.book.entity.Contact;
import com.address.book.exception.NotFoundException;
import com.address.book.repository.ContactRepository;
import com.address.book.service.ContactService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImp implements ContactService {

    private final String CONTACT_NOT_FOUND = "Contact not found";
    private static final ModelMapper modelMapper = new ModelMapper();
    private final ContactRepository repository;
    public ContactServiceImp(ContactRepository contactRepository) {
        this.repository = contactRepository;
    }


    public List<Contact> getAllContacts() {
        return orderByNameAsc(repository.findAll());
    }

    public List<ContactDTO> getContacts(String phrase){
        List<Contact> contacts = getAllContacts();
        if(phrase == null) {
            return toContactDTOList(contacts);
        }
        return toContactDTOList(filterByName(contacts, phrase));
    }

    @Override
    public ContactDTO getContact(int id) throws NotFoundException{
        List<Contact> contacts = getAllContacts();

        Contact contactDB = contacts.stream()
                .filter(contact -> contact.getId() == id)
                .findFirst()
                .orElse(null);

        if(contactDB == null) {
            throw new NotFoundException(CONTACT_NOT_FOUND);
        }

        return toContactDTO(contactDB);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        List<Contact> contacts = getAllContacts();

            Contact contactDB = contacts.stream()
                    .filter(contact -> contact.getId() == id)
                    .findFirst()
                    .orElse(null);

            if(contactDB == null) {
                throw new NotFoundException(CONTACT_NOT_FOUND);
            }

        repository.delete(id);
    }

    public List<Contact> filterByName( List<Contact> contacts, String phrase) {
        return contacts.stream()
                .filter(contact -> contact.getName().toLowerCase().contains(phrase.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Contact> orderByNameAsc(List<Contact> contacts) {
        contacts.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return contacts;
    }

    public static ContactDTO toContactDTO(Contact contact) {
        return modelMapper.map(contact, ContactDTO.class);
    }

    public static List<ContactDTO> toContactDTOList(List<Contact> contacts) {
        return contacts.stream()
                .map(contact -> modelMapper.map(contact, ContactDTO.class))
                .collect(Collectors.toList());
    }
}
