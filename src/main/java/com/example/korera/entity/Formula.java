package com.example.korera.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Formula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer formulaId;
    @ManyToOne
    private Project project;
    private String formulaName;
}
