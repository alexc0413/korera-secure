package com.example.korera.repository;

import com.example.korera.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ProjectRep extends JpaRepository<Project,Integer> {
}
