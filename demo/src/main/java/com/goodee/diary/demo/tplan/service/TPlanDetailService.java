package com.goodee.diary.demo.tplan.service;

import com.goodee.diary.demo.tplan.dao.TPlanDetailRepository;
import com.goodee.diary.demo.tplan.domain.TPlanDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TPlanDetailService {

    @Autowired
    TPlanDetailRepository tPlanDetailRepository;
    public List<TPlanDetailVO> findall() {
        List<TPlanDetailVO> list = new ArrayList<>();
        list = tPlanDetailRepository.findAll();
        return list;
    }
   public List<TPlanDetailVO> findAllById(int tplanId) {
       List<TPlanDetailVO> list = new ArrayList<>();
       List<TPlanDetailVO> selectedList = tPlanDetailRepository.findAll();
       for(TPlanDetailVO td:
               selectedList){
           if (td.getTplanID()==tplanId){
               list.add(td);
           }
       }
       return list;
   }

    @Transactional
    public TPlanDetailVO save(TPlanDetailVO tPlanDetailVO) {
        tPlanDetailRepository.save(tPlanDetailVO);
        return tPlanDetailVO;
    }
}
