package com.example.korera.service;

import com.example.korera.dto.ResponseProject;
import com.example.korera.entity.Project;

import java.util.List;

public interface ProjectService {
    Project createProject(Project project);

    void deleteProjectById(int id);

    Project updateProject(Project project);

    Project getProjectById(int id);

    List<Project> getAll();
}
