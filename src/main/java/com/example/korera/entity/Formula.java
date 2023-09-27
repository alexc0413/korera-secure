package com.example.korera.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
@Entity
@Builder
public class Formula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer formulaId;
    @ManyToOne
    private Project project;
    private String formulaName;

    public Formula() {
    }

    public Integer getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(Integer formulaId) {
        this.formulaId = formulaId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    @Override
    public String toString() {
        return "Formula{" +
                "formulaId=" + formulaId +
                ", project=" + project +
                ", formulaName='" + formulaName + '\'' +
                '}';
    }
}
