package com.example.demo.service;

import com.example.demo.model.dto.CreatePermissionRequestDto;
import com.example.demo.model.dto.PermissionDto;

import java.util.List;

public interface PermissionService {
    List<PermissionDto> getAllPermissions();
    PermissionDto createPermissionRequest(CreatePermissionRequestDto permissionRequestDto);
    PermissionDto updatePermission(Long id, PermissionDto permissionDto);
    void deletePermission(Long id);
    PermissionDto approvePermissionRequest(Long id);
    PermissionDto rejectPermissionRequest(Long id);
}
