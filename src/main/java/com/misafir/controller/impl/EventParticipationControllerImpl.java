package com.misafir.controller.impl;

import com.misafir.controller.IEventParticipationController;
import com.misafir.dto.DtoEventParticipation;
import com.misafir.entities.EventParticipation;
import com.misafir.services.EventParticipationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event-participation")
public class EventParticipationControllerImpl implements IEventParticipationController {

    @Autowired
    private EventParticipationService eventParticipationService;

    @Override
    @PostMapping
    public ResponseEntity<DtoEventParticipation> joinEvent(@RequestBody DtoEventParticipation dtoEventParticipation) {
        EventParticipation eventParticipation = new EventParticipation();
        BeanUtils.copyProperties(dtoEventParticipation, eventParticipation);
        eventParticipationService.joinEvent(eventParticipation);
        return ResponseEntity.status(201).body(dtoEventParticipation);
    }
}
