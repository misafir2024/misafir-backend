package com.misafir.controller;

import com.misafir.dto.DtoEvent;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface IEventController {
    ResponseEntity<DtoEvent> createEvent(DtoEvent dtoEvent);
    List<DtoEvent> getAllEvents();
}
