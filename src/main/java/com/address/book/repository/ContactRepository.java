package com.address.book.repository;
import com.address.book.entity.Contact;
import java.util.List;

public interface ContactRepository{
    List<Contact> findAll();
    void delete(int id);
}
