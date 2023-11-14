package com.address.book.controller;
import com.address.book.domain.response.ContactDTO;
import com.address.book.entity.Contact;
import com.address.book.exception.NotFoundException;
import com.address.book.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(value = "/contacts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ContactDTO>> getAll(@RequestParam(required = false, name = "phrase") String phrase){
        return ResponseEntity.ok(contactService.getContacts(phrase));
    }

    @GetMapping("/contacts/{contactId}")
    public ResponseEntity<ContactDTO> getOne(@PathVariable(name = "contactId") int id) throws NotFoundException {
        return ResponseEntity.ok(contactService.getContact(id));
    }

    @DeleteMapping("/contacts/{contactId}")
    public ResponseEntity<Object> delete(@PathVariable(name = "contactId") int id) throws NotFoundException {
        contactService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
