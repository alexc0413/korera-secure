package com.example.korera.controlletest;
import com.example.korera.controller.*;
import com.example.korera.entity.Formula;
import com.example.korera.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;


@WebMvcTest(FormulaController.class)
public class FormulaControllerTest {

    @MockBean
    private FormulaService formulaService;

    @Autowired
    private MockMvc mockMvc;

    private Formula formula;
    private String jsonContent;
    @BeforeEach
    public void setUp() throws JsonProcessingException {
        formula = new Formula();
        formula.setFormulaId(1);
        formula.setFormulaName("formula");
        jsonContent = new ObjectMapper().writeValueAsString(formula);
    }

    @Test
    public void formulaCreate() throws Exception {
        when(formulaService.createFormula(any(Formula.class))).thenReturn(formula);
        mockMvc.perform(post("/formula/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.formulaId",is(1)))
                .andExpect(jsonPath("$.formulaName",is("formula")));
    }
    @Test
    public void formulaDelete() throws Exception {
        when(formulaService.deleteFormulaById(1)).thenReturn(formula);
        mockMvc.perform(delete("/formula/delete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.formulaId",is(1)))
                .andExpect(jsonPath("$.formulaName",is("formula")));
    }
    @Test
    public void formulaUpdate() throws Exception {
        when(formulaService.updateFormula(any(Formula.class))).thenReturn(formula);
        mockMvc.perform(put("/formula/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.formulaId",is(1)))
                .andExpect(jsonPath("$.formulaName",is("formula")));
    }
    @Test
    public void formulaGetById() throws Exception {
        when(formulaService.getFormulaById(1)).thenReturn(formula);
        mockMvc.perform(get("/formula/get/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.formulaId",is(1)))
                .andExpect(jsonPath("$.formulaName",is("formula")));
    }
    @Test
    public void formulaGetAll() throws Exception {
        List<Formula> list = new ArrayList<>();
        list.add(formula);
        Formula formula1 = new Formula();
        formula1.setFormulaId(2);
        formula1.setFormulaName("this is formula");
        list.add(formula1);
        when(formulaService.getAll()).thenReturn(list);
        mockMvc.perform(get("/formula/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].formulaId", is(1)))
                .andExpect(jsonPath("$[0].formulaName", is("formula")))
                .andExpect(jsonPath("$[1].formulaId", is(2)))
                .andExpect(jsonPath("$[1].formulaName", is("this is formula")));
    }
}
