package com.example.korera.service;

import com.example.korera.entity.Resource;

import java.util.List;

public interface ResourceService {
    Resource createResource(Resource resource);

    void deleteResourceById(Integer id);

    Resource updateResource(Resource resource) throws Exception;

    Resource getResourceById(Integer id);

    List<Resource> getAll();
}
