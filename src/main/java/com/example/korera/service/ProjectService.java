package com.example.korera.service;

import com.example.korera.entity.Project;

import java.util.List;

public interface ProjectService {
    Project createProject(Project project);

    Project deleteProjectById(int id);

    Project updateProject(Project project);

    Project getProjectById(int id);

    List<Project> getAll();
}
