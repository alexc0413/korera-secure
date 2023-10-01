package com.example.korera.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.*;

//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "pid")
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("pid")
    private Integer projectId;

    @ToString.Exclude
    @ManyToOne
    private User user;

//    @NotNull(message = "this field cannot be empty when updating")
    @JsonManagedReference
    @ToString.Exclude
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    // this field cannot be dereferenced since orphanRemoval = true
    private List<Formula> formulas;

    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "name cannot be empty")
    private String projectName;

    @CreatedDate
    private LocalDateTime createDate;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(
            name = "project_resource",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
//    @JsonBackReference
    private Set<Resource> resources = new HashSet<>();

    public Project(Integer id, String name) {
        this.projectId = id;
        this.projectName = name;
    }


    @JsonGetter("Resources ID")
    public List<Integer> getResourcesId(){
        List<Integer> list = new ArrayList<>();
        for(Resource r : resources){
            list.add(r.getResourceId());
        }
        return list;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId);
    }

}
