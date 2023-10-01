package com.example.korera.service;

import com.example.korera.entity.ResourceDetail;

import java.util.List;

public interface ResourceDetailService {
    ResourceDetail createResourceDetail(ResourceDetail resourceDetail);

    void deleteResourceDetailById(Integer id);

    ResourceDetail updateResourceDetail(ResourceDetail resourceDetail);

    ResourceDetail getResourceDetailById(Integer id);

    List<ResourceDetail> getAll();
}
