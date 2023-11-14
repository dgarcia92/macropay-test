package com.address.book.repository;
import com.address.book.domain.response.ContactDTO;
import com.address.book.entity.Contact;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContactRepositoryImp implements ContactRepository{

    private static final Logger logger = LoggerFactory.getLogger(ContactRepositoryImp.class);

    Gson gson = new Gson();
    private List<Contact> contacts = new ArrayList<>();
    public ContactRepositoryImp(){
        try (FileReader reader = new FileReader("db.json")) {
            Type contactListType = new TypeToken<List<Contact>>() {}.getType();
            contacts = gson.fromJson(reader, contactListType);
        } catch (IOException e) {
            logger.error("Error to read db.json", e.getMessage());
        }
    }

    @Override
    public List<Contact> findAll() {
        return contacts;
    }

    public void delete(int id) {
        contacts.removeIf(contact -> contact.getId() == id);
    }
}
