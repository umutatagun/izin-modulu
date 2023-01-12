package com.example.demo.controller;

import com.example.demo.model.dto.CreatePermissionRequestDto;
import com.example.demo.model.dto.PermissionDto;
import com.example.demo.service.PermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/permission")
public class PermissionController {
    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public ResponseEntity<List<PermissionDto>> getAllPermissions(){
        return new ResponseEntity(permissionService.getAllPermissions(), OK);
    }

    @PostMapping
    public ResponseEntity<PermissionDto> createPermission(@RequestBody CreatePermissionRequestDto createPermissionRequestDto) {
        return new ResponseEntity(permissionService.createPermissionRequest(createPermissionRequestDto), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PermissionDto> updatePermission(@PathVariable Long id, @RequestBody PermissionDto permissionDto) {
        return new ResponseEntity(permissionService.updatePermission(id, permissionDto), CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return new ResponseEntity(OK);
    }

    @PatchMapping("/approve/{id}")
    public ResponseEntity<PermissionDto> approvePermissionRequest(@PathVariable Long id) {
        return new ResponseEntity(permissionService.approvePermissionRequest(id), OK);
    }

    @PatchMapping("/reject/{id}")
    public ResponseEntity<PermissionDto> rejectPermissionRequest(@PathVariable Long id) {
        return new ResponseEntity(permissionService.rejectPermissionRequest(id), OK);
    }
}
