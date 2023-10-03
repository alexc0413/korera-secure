package com.example.korera.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = ResourceDetail.class)
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "resourceId")
    @JsonBackReference(value = "ResourceAndDetail")
    private Resource resource;
    private String detail;

    @JsonGetter("Resource ID")
    public Integer getInfo(){
        return this.resource.getResourceId();
    }

    @Override
    public String toString() {
        return "ResourceDetail{" +
                "id=" + id +
                ", detail='" + detail + '\'' +
                '}';
    }
}
