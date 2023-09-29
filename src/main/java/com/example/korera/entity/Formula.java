package com.example.korera.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "formulaId")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Formula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer formulaId;
//    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "project_id")
    private Project project;

    private String formulaName;
}
