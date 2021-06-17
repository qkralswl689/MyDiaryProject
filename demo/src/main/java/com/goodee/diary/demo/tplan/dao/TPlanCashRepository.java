package com.goodee.diary.demo.tplan.dao;

import com.goodee.diary.demo.tplan.domain.TPlanCash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TPlanCashRepository extends JpaRepository<TPlanCash, Integer> {
    List<TPlanCash> findAll();

    TPlanCash save(TPlanCash tPlanCash);



}
