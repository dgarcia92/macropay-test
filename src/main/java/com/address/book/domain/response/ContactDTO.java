package com.address.book.domain.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ContactDTO {
    @JsonProperty("contact_id")
    private Long id;
    private String name;
    private String phone;
    @JsonProperty("address_lines")
    private List<String> addressLines;
}
