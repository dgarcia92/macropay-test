package com.address.book.entity;

import lombok.Data;
import java.util.List;

@Data
public class Contact {
    private Long id;
    private String name;
    private String phone;
    private List<String> addressLines;
}
