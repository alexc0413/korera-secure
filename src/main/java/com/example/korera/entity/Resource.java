package com.example.korera.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.*;

@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer resourceId;
    private String description;

    @CreatedDate
    private LocalDateTime timeCreated;

    @LastModifiedDate
    private LocalDateTime lastModify;

    @ToString.Exclude
    @ManyToMany(mappedBy = "resources")
    @JsonBackReference
    private Set<Project> projects = new HashSet<>();

    @ToString.Exclude
    @OneToOne(mappedBy = "resource", cascade = CascadeType.ALL)
    private ResourceDetail resourceDetail;

    @JsonGetter("Project ID")
    public List<Integer> getAllProjectId(){
        List<Integer> list = new ArrayList<>();
        for(Project r : projects){
            list.add(r.getProjectId());
        }
        return list;
    }

    @Override
    public int hashCode() {
        return Objects.hash(resourceId);
    }

}
