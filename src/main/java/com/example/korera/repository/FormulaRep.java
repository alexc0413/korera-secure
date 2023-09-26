package com.example.korera.repository;

import com.example.korera.entity.Formula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormulaRep  extends JpaRepository<Formula,Integer> {
}
