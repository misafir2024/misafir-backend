package com.misafir.controller.impl;

import com.misafir.controller.IHostController;
import com.misafir.dto.DtoHost;
import com.misafir.services.IHostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guest")
public class HostControllerImpl implements IHostController {

    @Autowired
    private IHostService hostService;

    @Override
    @PostMapping("/create-host")
    public ResponseEntity<DtoHost> createHost(@Valid @RequestBody DtoHost dtoHost) {
        DtoHost createdHost = hostService.saveHost(dtoHost);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHost);
    }

    @Override
    @GetMapping("/host-list")
    public List<DtoHost> getAllHosts() {
        return hostService.getAllHosts();
    }
}
