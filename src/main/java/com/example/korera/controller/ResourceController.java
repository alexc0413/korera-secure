package com.example.korera.controller;


import com.example.korera.entity.Resource;
import com.example.korera.service.ResourceService;
import com.example.korera.service.ResourceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resource")
public class ResourceController {
    private final ResourceService resourceServiceImp;

    @Autowired
    public ResourceController(ResourceService resourceServiceImp) {
        this.resourceServiceImp = resourceServiceImp;
    }

    @PostMapping("/create")
    public ResponseEntity<Resource> createResource(@RequestBody Resource resource) {
        Resource resource1 = resourceServiceImp.createResource(resource);
        return new ResponseEntity<>(resource1, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Resource> deleteResource(@PathVariable Integer id) {
        Resource resource = resourceServiceImp.deleteResourceById(id);
        return new ResponseEntity<>(resource, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<Resource> updateResource(Resource resource) {
        Resource r = resourceServiceImp.updateResource(resource);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable Integer id) {
        Resource r = resourceServiceImp.getResourceById(id);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Resource>> getAll() {
        List<Resource> resources = resourceServiceImp.getAll();
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }
}
