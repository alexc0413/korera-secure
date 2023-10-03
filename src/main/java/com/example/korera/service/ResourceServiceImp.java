package com.example.korera.service;


import com.example.korera.entity.Project;
import com.example.korera.entity.Resource;
import com.example.korera.exceptions.CreationException;
import com.example.korera.exceptions.ResourceDetailNotFoundException;
import com.example.korera.repository.ProjectRep;
import com.example.korera.repository.ResourceRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ResourceServiceImp implements ResourceService {

    private final ResourceRep resourceRep;
    private final ProjectRep projectRep;

    @Autowired
    public ResourceServiceImp(ResourceRep resourceRep, ProjectRep projectRep) {
        this.resourceRep = resourceRep;
        this.projectRep = projectRep;
    }

    @Override
    public Resource createResource(Resource resource) {
        resourceRep.save(resource);
        Optional<Resource> resource1 = resourceRep.findById(resource.getResourceId());
        if(resource1.isEmpty()){
            throw new CreationException("cannot create");
        }
        return resource;
    }

    @Override
    public void deleteResourceById(Integer id) {
        Optional<Resource> optional = resourceRep.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceDetailNotFoundException("Not existed");
        }
        resourceRep.deleteById(id);
    }

    @Override
    public Resource updateResource(Resource resource) throws Exception {
        Integer id = resource.getResourceId();
        Optional<Resource> optional = resourceRep.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceDetailNotFoundException("Not existed");
        }
        resourceRep.save(resource);
        Set<Project> projects = optional.get().getProjects();
        for(Project p : projects){
            p.getResources().add(resource);
            projectRep.save(p);
        }
        return optional.get();
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
