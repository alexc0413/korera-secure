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

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "pid",
        scope = Project.class)
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


    @ManyToOne
    private User user;


    @JsonManagedReference(value = "projectAndFormulas")
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    // this field cannot be dereferenced since orphanRemoval = true
    private List<Formula> formulas;

    @NotNull(message = "name cannot be null")
    @NotEmpty(message = "name cannot be empty")
    private String projectName;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "project_resource",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id")
    )
    private Set<Resource> resources = new HashSet<>();

    @JsonGetter("Resources ID")
    public List<Integer> getResourcesId() {
        List<Integer> list = new ArrayList<>();
        for (Resource r : resources) {
            list.add(r.getResourceId());
        }
        return list;
    }

    @JsonGetter("UserName")
    public String retrieveUserName() {
        if (this.user != null) {
            return this.user.getUserName();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project project)) return false;
        return Objects.equals(getProjectId(), project.getProjectId()) && Objects.equals(getProjectName(), project.getProjectName()) && Objects.equals(getCreateDate(), project.getCreateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProjectId(), getProjectName(), getCreateDate());
    }
}
