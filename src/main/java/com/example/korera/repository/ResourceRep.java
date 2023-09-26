package com.example.korera.repository;

import com.example.korera.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ResourceRep extends JpaRepository<Resource,Integer> {
}
