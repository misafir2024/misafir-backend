package com.misafir.services;

import com.misafir.dto.DtoHost;
import java.util.List;

public interface IHostService {
    DtoHost saveHost(DtoHost dtoHost);
    List<DtoHost> getAllHosts();
}
