package com.example.korera.controller;

import com.example.korera.entity.Formula;
import com.example.korera.service.FormulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formula")
public class FormulaController {

    private final FormulaService formulaService;

    @Autowired
    public FormulaController(FormulaService formulaService) {
        this.formulaService = formulaService;
    }

    @PostMapping("/create")
    public ResponseEntity<Formula> createFormula(@RequestBody Formula formula) {
        Formula f = formulaService.createFormula(formula);
        return new ResponseEntity<>(f, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Formula> deleteFormula(@PathVariable int id) {

        Formula f = formulaService.deleteFormulaById(id);
        return new ResponseEntity<>(f, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update")
    public ResponseEntity<Formula> updateFormula(@RequestBody Formula formula) {
        Formula f = formulaService.updateFormula(formula);
        return new ResponseEntity<>(f, HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Formula> getFormulaById(@PathVariable int id) {
        Formula f = formulaService.getFormulaById(id);
        return new ResponseEntity<>(f, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Formula>> getAllFormula() {
        List<Formula> formulas = formulaService.getAll();
        return new ResponseEntity<>(formulas, HttpStatus.OK);
    }

}
