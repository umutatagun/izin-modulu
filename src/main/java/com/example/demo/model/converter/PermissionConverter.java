package com.example.demo.model.converter;

import com.example.demo.model.Permission;
import com.example.demo.model.dto.CreatePermissionRequestDto;
import com.example.demo.model.dto.PermissionDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PermissionConverter {
    private final ModelMapper modelMapper;

    public PermissionConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PermissionDto convert(Permission from) {
        return modelMapper.map(from, PermissionDto.class);
    }

    public Permission convert(PermissionDto from) {
        return modelMapper.map(from, Permission.class);
    }

    public Permission convert(CreatePermissionRequestDto from) {
        return modelMapper.map(from, Permission.class);
    }
}
