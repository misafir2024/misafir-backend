package com.misafir.services;

import com.misafir.dto.DtoEvent;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EventService {
    DtoEvent createEvent(DtoEvent dtoEvent, List<MultipartFile> images) throws IOException;
    List<DtoEvent> getAllEvents();
}
