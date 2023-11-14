package com.address.book.service;
import com.address.book.domain.response.ContactDTO;
import com.address.book.entity.Contact;
import com.address.book.exception.NotFoundException;

import java.util.List;

public interface ContactService {
    List<ContactDTO> getContacts(String phrase);
    ContactDTO getContact(int id) throws NotFoundException;
    void delete(int id) throws NotFoundException;
}
