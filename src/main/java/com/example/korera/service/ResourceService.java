package com.example.korera.service;

import com.example.korera.entity.Resource;

import java.util.List;

public interface ResourceService {
    Resource createResource(Resource resource);

    Resource deleteResourceById(Integer id);

    Resource updateResource(Resource resource);

    Resource getResourceById(Integer id);

    List<Resource> getAll();
}
