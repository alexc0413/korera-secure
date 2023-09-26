package com.example.korera;


import com.example.korera.entity.Project;
import com.example.korera.entity.User;
import com.example.korera.repository.FormulaRep;
import com.example.korera.repository.ProjectRep;
import com.example.korera.repository.ResourceRep;
import com.example.korera.repository.UserRep;
import com.example.korera.util.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProjectRepTest {


    @Autowired
    private FormulaRep formulaRep;
    @Autowired
    private ProjectRep projectRep;
    @Autowired
    private ResourceRep resourceRep;
    @Autowired
    private UserRep userRep;

    @Test
    public void testSaveUser() {
        User user = User.builder().userName("Zeyu").password("1234").role(Role.MEMBER).build();
        User u = userRep.save(user);
        assertThat(u).isNotNull();
        assertThat(u.getRole()).isNotNull();
//        assertThat(u).isEqualToComparingFieldByField(user);
        // add more assertions based on your needs
    }

    @Test
    public void testUpload() {
        Project project = new Project();
        project.setProjectName("Test Project");
        // set other fields

        Project savedProject = projectRep.save(project);

        assertThat(savedProject).isNotNull();
        assertThat(savedProject.getProjectId()).isNotNull();
        // add more assertions based on your needs
    }
}
