package com.example.korera.service;


import com.example.korera.entity.Resource;
import com.example.korera.entity.ResourceDetail;
import com.example.korera.exceptions.CreationException;
import com.example.korera.exceptions.ResourceDetailNotFoundException;
import com.example.korera.exceptions.ResourceNotFoundException;
import com.example.korera.repository.ResourceDetailRep;
import com.example.korera.repository.ResourceRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceDetailServiceImp implements ResourceDetailService {

    private final ResourceDetailRep resourceDetailRep;
    private final ResourceRep resourceRep;

    @Autowired
    public ResourceDetailServiceImp(ResourceDetailRep resourceDetailRep, ResourceRep resourceRep) {
        this.resourceDetailRep = resourceDetailRep;
        this.resourceRep = resourceRep;
    }

    @Override
    public ResourceDetail createResourceDetail(ResourceDetail resourceDetail) {
        Optional<Resource> optionalResource = resourceRep.findById(resourceDetail.getResource().getResourceId());
        if(optionalResource.isEmpty()){
            throw new ResourceNotFoundException("Resource is not existed");
        }
        resourceDetail.setResource(optionalResource.get());
        resourceDetailRep.save(resourceDetail);
        return resourceDetail;
    }

    @Override
    public void deleteResourceDetailById(Integer id) {
        Optional<ResourceDetail> optional = resourceDetailRep.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceDetailNotFoundException("Not existed");
        }
        ResourceDetail dbResourceDetail = optional.get();
        dbResourceDetail.getResource().setResourceDetail(null);
        resourceDetailRep.deleteById(id);
    }

    @Override
    public ResourceDetail updateResourceDetail(ResourceDetail resourceDetail) {
        Integer id = resourceDetail.getId();
        Optional<ResourceDetail> optional = resourceDetailRep.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceDetailNotFoundException("Not existed");
        }
        resourceDetailRep.save(resourceDetail);
        return resourceDetail;
    }

    @Override
    public ResourceDetail getResourceDetailById(Integer id) {
        Optional<ResourceDetail> optional = resourceDetailRep.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceDetailNotFoundException("Not existed");
        }
        return optional.get();
    }

    @Override
    public List<ResourceDetail> getAll() {
        return resourceDetailRep.findAll();
    }

}
