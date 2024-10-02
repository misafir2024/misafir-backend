package com.misafir.controller;

import com.misafir.dto.DtoHost;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IHostController {
    DtoHost createHost(@RequestBody DtoHost dtoHost);
    List<DtoHost> getAllHosts();
}
