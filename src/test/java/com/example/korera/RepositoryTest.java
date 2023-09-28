package com.example.korera;


import com.example.korera.repository.FormulaRep;
import com.example.korera.repository.ProjectRep;
import com.example.korera.repository.ResourceRep;
import com.example.korera.repository.UserRep;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RepositoryTest {


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

    }

    @Test
    public void testUpload() {

    }
}
