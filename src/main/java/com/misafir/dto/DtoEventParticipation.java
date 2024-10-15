package com.misafir.dto;

import lombok.Data;

@Data
public class DtoEventParticipation {
    private Long eventId;
    private Long userId;
    private boolean paid;
}
