package com.misafir.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DtoHost {
    private Long id;
    private String firstName;
    private String lastName;
    private Date birthOfDate;
    private String email;
}
