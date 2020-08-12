package com.example.project_def.service.impl;

import com.example.project_def.model.entity.URole;
import com.example.project_def.repository.URoleRepository;
import com.example.project_def.service.URoleService;
import org.modelmapper.ModelMapper;
import com.example.project_def.enums.RoleName;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class URoleServiceImpl implements URoleService {

    private final URoleRepository uRoleRepository;
    private final ModelMapper modelMapper;

    public URoleServiceImpl(URoleRepository uRoleRepository, ModelMapper modelMapper) {
        this.uRoleRepository = uRoleRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    @Override
    public void seedRole() {
        if (this.uRoleRepository.count() == 0) {
            for (RoleName value : RoleName.values()) {
                this.uRoleRepository.saveAndFlush(new URole(value.name()));
            }
        }
    }
}
