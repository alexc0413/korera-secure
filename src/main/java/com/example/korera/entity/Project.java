package com.example.korera.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer projectId;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "project")
    private List<Formula> formulas;

    private String projectName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "project_resource",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private Set<Resource> resources = new HashSet<>();

}
