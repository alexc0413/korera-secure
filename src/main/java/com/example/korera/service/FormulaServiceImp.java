package com.example.korera.service;

import com.example.korera.entity.Formula;
import com.example.korera.exceptions.FormulaNotFoundException;
import com.example.korera.repository.FormulaRep;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class FormulaServiceImp implements FormulaService {
    private final FormulaRep formulaRep;

    @Autowired
    public FormulaServiceImp(FormulaRep formulaRep){
        this.formulaRep = formulaRep;
    }

    @Override
    public Formula createFormula(Formula formula){
        formulaRep.save(formula);
        return formula;
    }

    @Override
    public Formula deleteFormulaById(int id){
        Formula Formula = getFormulaById(id);
        if(Formula==null){
            throw new FormulaNotFoundException("Formula is not found");
        }
        formulaRep.deleteById(id);
        return Formula;
    }

    @Override
    public Formula updateFormula(Formula formula){
        if(formula==null){
            throw new FormulaNotFoundException("Formula is not found");
        }
        formulaRep.save(formula);
        return formula;
    }

    @Override
    public Formula getFormulaById(int id){
        Optional<Formula> Formula = formulaRep.findById(id);
        if(Formula.isEmpty()){
            throw new FormulaNotFoundException("Formula is not found");
        }
        return Formula.get();
    }

    @Override
    public List<Formula> getAll(){
        return formulaRep.findAll();
    }
}
