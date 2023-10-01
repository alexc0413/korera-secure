package com.example.korera.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Formula {
    @Id
    @JsonProperty("fid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer formulaId;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "project_id")
    private Project project;

    private String formulaName;

    @JsonGetter("project_id")
    public Integer getProjectId() {
        return this.project != null ? this.project.getProjectId() : null;
    }
}
