package com.misafir.controller;

import com.misafir.dto.DtoHost;
import com.misafir.services.IHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hosts")
public class HostController {

    @Autowired
    private IHostService hostService;

    @PostMapping
    public DtoHost createHost(@RequestBody DtoHost dtoHost) {
        return hostService.saveHost(dtoHost);
    }

    @GetMapping
    public List<DtoHost> getAllHosts() {
        return hostService.getAllHosts();
    }
}
