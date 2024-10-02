package com.misafir.services.impl;

import com.misafir.dto.DtoHost;
import com.misafir.entities.Host;
import com.misafir.repositories.HostRepository;
import com.misafir.services.IHostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HostServiceImpl implements IHostService {

    @Autowired
    private HostRepository hostRepository;

    @Override
    public DtoHost saveHost(DtoHost dtoHost) {
        Host host = new Host();
        BeanUtils.copyProperties(dtoHost, host);
        host = hostRepository.save(host);
        DtoHost result = new DtoHost();
        BeanUtils.copyProperties(host, result);
        return result;
    }

    @Override
    public List<DtoHost> getAllHosts() {
        return hostRepository.findAll()
                .stream()
                .map(host -> {
                    DtoHost dtoHost = new DtoHost();
                    BeanUtils.copyProperties(host, dtoHost);
                    return dtoHost;
                })
                .collect(Collectors.toList());
    }
}
