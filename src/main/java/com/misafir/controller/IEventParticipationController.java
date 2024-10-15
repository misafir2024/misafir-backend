package com.misafir.controller;


import com.misafir.dto.DtoEventParticipation;
import org.springframework.http.ResponseEntity;

public interface IEventParticipationController {
    ResponseEntity<DtoEventParticipation> joinEvent(DtoEventParticipation dtoEventParticipation);
}