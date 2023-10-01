package com.example.korera.service;

import com.example.korera.entity.Formula;

import java.util.List;

public interface FormulaService {
    Formula createFormula(Formula formula);

    void deleteFormulaById(int id);

    Formula updateFormula(Formula formula);

    Formula getFormulaById(int id);

    List<Formula> getAll();
}
