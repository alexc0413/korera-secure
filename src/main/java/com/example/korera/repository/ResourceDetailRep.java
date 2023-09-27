package com.example.korera.repository;

import com.example.korera.entity.ResourceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceDetailRep extends JpaRepository<ResourceDetail,Integer> {
}
