package com.goodee.diary.demo.tplan.service;

import com.goodee.diary.demo.tplan.dao.TplanRepository;
import com.goodee.diary.demo.tplan.domain.TPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TPlanService {

    @Autowired
    TplanRepository tplanRepository;
    public List<TPlan> findall() {
        List<TPlan> list = new ArrayList<>();
        list = tplanRepository.findAll();
        return list;
    }
    public List<TPlan> findAllById(int userid){
        List<TPlan> list = new ArrayList<>();
        List<TPlan> selectecdList = tplanRepository.findAll();
        for (TPlan t :
              selectecdList) {
            if(t.getUserId() == userid){
                list.add(t);
            }
        }
        return list;
    }

    @Transactional
    public TPlan save(TPlan tPlan) {
        tplanRepository.save(tPlan);
        return tPlan;
    }
//
//    TPlan createTplan(TPlan tPlan);
//    List<TPlan> findall();

}
