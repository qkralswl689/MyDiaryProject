package com.goodee.diary.demo.tplan.service;

import com.goodee.diary.demo.tplan.dao.TPlanCashRepository;
import com.goodee.diary.demo.tplan.domain.TPlanCash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TPlanCashService {

    @Autowired
    TPlanCashRepository tPlanCashRepository;
    public List<TPlanCash> findall() {
        List<TPlanCash> list = new ArrayList<>();
        list = tPlanCashRepository.findAll();
        return list;
    }

    @Transactional
    public TPlanCash save(TPlanCash tPlanCash) {
        tPlanCashRepository.save(tPlanCash);
        return tPlanCash;
    }
}
