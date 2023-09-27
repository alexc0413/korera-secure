package com.example.korera.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@EntityListeners(AuditingEntityListener.class)
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
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
    @ManyToMany(mappedBy = "resources")
    private Set<Project> projects = new HashSet<>();
    @OneToOne(mappedBy = "resource",cascade = CascadeType.ALL)
    private ResourceDetail resourceDetail;

    public Resource() {
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
    }

    public LocalDateTime getLastModify() {
        return lastModify;
    }

    public void setLastModify(LocalDateTime lastModify) {
        this.lastModify = lastModify;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public ResourceDetail getResourceDetail() {
        return resourceDetail;
    }

    public void setResourceDetail(ResourceDetail resourceDetail) {
        this.resourceDetail = resourceDetail;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId=" + resourceId +
                ", description='" + description + '\'' +
                ", timeCreated=" + timeCreated +
                ", lastModify=" + lastModify +
                ", projects=" + projects +
                ", resourceDetail=" + resourceDetail +
                '}';
    }
}
