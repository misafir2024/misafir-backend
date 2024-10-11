package com.misafir.dto;

import lombok.Data;

@Data
public class DtoListing {
    private Long id;
    private String eventType;
    private String host;
    private String imageUrl;
}
