package com.misafir.services;

import com.misafir.dto.DtoEvent;
import java.util.List;

// services/EventService.java
public interface EventService {
    DtoEvent createEvent(DtoEvent dtoEvent);
    List<DtoEvent> getAllEvents();
}
