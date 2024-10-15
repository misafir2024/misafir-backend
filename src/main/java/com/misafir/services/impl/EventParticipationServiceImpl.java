package com.misafir.services.impl;

import com.misafir.entities.EventParticipation;
import com.misafir.repositories.EventParticipationRepository;
import com.misafir.services.EventParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventParticipationServiceImpl implements EventParticipationService {

    @Autowired
    private EventParticipationRepository eventParticipationRepository;

    @Override
    public EventParticipation joinEvent(EventParticipation eventParticipation) {
        return eventParticipationRepository.save(eventParticipation);
    }
}
