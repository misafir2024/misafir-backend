package com.misafir.dto;

import lombok.Data;

@Data
public class DtoListing {
    private Long id;
    private String eventType;
    private String hostName;  // Change this to display the host name
    private String imageUrl;
}
