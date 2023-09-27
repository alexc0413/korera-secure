package com.example.korera.service;


import com.example.korera.entity.Resource;
import com.example.korera.exceptions.ResourceDetailNotFoundException;
import com.example.korera.repository.ResourceRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceServiceImp implements ResourceService {

    private final ResourceRep resourceRep;

    @Autowired
    public ResourceServiceImp(ResourceRep resourceRep) {
        this.resourceRep = resourceRep;
    }

    @Override
    public Resource createResource(Resource resource) {
        resourceRep.save(resource);
        return resource;
    }

    @Override
    public Resource deleteResourceById(Integer id) {
        Optional<Resource> optional = resourceRep.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceDetailNotFoundException("Not existed");
        }
        resourceRep.deleteById(id);
        return optional.get();
    }

    @Override
    public Resource updateResource(Resource resource) {
        Integer id = resource.getResourceId();
        Optional<Resource> optional = resourceRep.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceDetailNotFoundException("Not existed");
        }
        return resourceRep.save(resource);
    }

    @Override
    public Resource getResourceById(Integer id) {
        Optional<Resource> optional = resourceRep.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceDetailNotFoundException("Not existed");
        }
        return optional.get();
    }

    @Override
    public List<Resource> getAll() {
        return resourceRep.findAll();
    }
}
