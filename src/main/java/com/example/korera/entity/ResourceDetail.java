package com.example.korera.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
//@Data
@AllArgsConstructor
//@NoArgsConstructor
public class ResourceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne
    @MapsId
    @JoinColumn(name = "resourceId")
    private Resource resource;
    private String detail;

    public ResourceDetail() {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "ResourceDetail{" +
                "id=" + id +
                ", resource=" + resource +
                ", detail='" + detail + '\'' +
                '}';
    }
}
