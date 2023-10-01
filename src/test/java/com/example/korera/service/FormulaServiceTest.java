package com.example.korera.service;

import com.example.korera.entity.Formula;
import com.example.korera.repository.FormulaRep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FormulaServiceTest {

    @Autowired
    private FormulaService formulaService;

    @MockBean
    private FormulaRep formulaRep;
    private Formula formula;
    @BeforeEach
    void setUp(){
        formula = Formula.builder().formulaId(1).formulaName("first").build();

    }

    @Test
    void createFormula() {
        Mockito.when(formulaRep.save(formula)).thenReturn(formula);
        Mockito.when(formulaRep.findById(formula.getFormulaId())).thenReturn(Optional.ofNullable(formula));
        assertEquals(formula,formulaService.createFormula(formula));
    }

    @Test
    void deleteFormulaById() {
        Mockito.when(formulaRep.findById(formula.getFormulaId())).thenReturn(Optional.ofNullable(formula));
        assertEquals(formula,formulaService.deleteFormulaById(1));
    }

//    @Test
//    void updateFormula() {
//        Mockito.when(formulaRep.save(formula)).thenReturn(formula);
//        assertEquals(formula,formulaService.updateFormula(formula));
//    }

    @Test
    void getFormulaById() {
        Mockito.when(formulaRep.findById(1)).thenReturn(Optional.ofNullable(formula));
        assertEquals(formula,formulaService.getFormulaById(1));
    }

    @Test
    void getAll() {
        List<Formula> list = new ArrayList<>();
        list.add(formula);
        Mockito.when(formulaRep.findAll()).thenReturn(list);
        assertEquals(list,formulaService.getAll());
    }
}