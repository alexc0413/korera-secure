package com.example.korera.service;


import com.example.korera.entity.ResourceDetail;
import com.example.korera.exceptions.CreationException;
import com.example.korera.exceptions.ResourceDetailNotFoundException;
import com.example.korera.repository.ResourceDetailRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceDetailServiceImp implements ResourceDetailService {

    private final ResourceDetailRep resourceDetailRep;

    @Autowired
    public ResourceDetailServiceImp(ResourceDetailRep resourceDetailRep) {
        this.resourceDetailRep = resourceDetailRep;
    }

    @Override
    public ResourceDetail createResourceDetail(ResourceDetail resourceDetail) {
        resourceDetailRep.save(resourceDetail);
        Optional<ResourceDetail> resourceDetail1 = resourceDetailRep.findById(resourceDetail.getId());
        if(resourceDetail1.isEmpty()){
            throw new CreationException("cannot create");
        }
        return resourceDetail1.get();
    }

    @Override
    public void deleteResourceDetailById(Integer id) {
        Optional<ResourceDetail> optional = resourceDetailRep.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceDetailNotFoundException("Not existed");
        }
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
