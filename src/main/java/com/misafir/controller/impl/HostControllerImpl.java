package com.misafir.controller.impl;

import com.misafir.controller.IHostController;
import com.misafir.dto.DtoHost;
import com.misafir.services.IHostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guest")
public class HostControllerImpl implements IHostController {

    @Autowired
    private IHostService hostService;

    @Override
    @PostMapping("/create-host")
    public DtoHost createHost(@RequestBody DtoHost dtoHost) {
        return hostService.saveHost(dtoHost);
    }

    @Override
    @GetMapping("/host-list")
    public List<DtoHost> getAllHosts() {
        return hostService.getAllHosts();
    }
}
