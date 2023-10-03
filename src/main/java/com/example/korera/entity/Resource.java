package com.example.korera.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.*;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "rid",
        scope = Resource.class)
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("rid")
    private Integer resourceId;

    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timeCreated;

    @LastModifiedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModify;

    @JsonBackReference
    @ManyToMany(mappedBy = "resources", fetch = FetchType.EAGER)
    private Set<Project> projects = new HashSet<>();

//    @JsonManagedReference(value = "ResourceAndDetail")
    @OneToOne(mappedBy = "resource")
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resource resource)) return false;
        return Objects.equals(getResourceId(), resource.getResourceId()) && Objects.equals(getDescription(), resource.getDescription()) && Objects.equals(getTimeCreated(), resource.getTimeCreated()) && Objects.equals(getLastModify(), resource.getLastModify());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResourceId(), getDescription(), getTimeCreated(), getLastModify());
    }

    @Override
    public String toString() {
        return "Resource{" +
                "resourceId=" + resourceId +
                ", description='" + description + '\'' +
                ", timeCreated=" + timeCreated +
                ", lastModify=" + lastModify +
                '}';
    }
}
