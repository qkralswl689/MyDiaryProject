package com.goodee.diary.demo.spending.service;

import com.goodee.diary.demo.spending.dao.SpendingRepository;
import com.goodee.diary.demo.spending.domain.Spending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SpendingService {

    @Autowired
    SpendingRepository spendingRepository;

    //모든 유저
    @Transactional(readOnly = true)
    public List<Spending> getAll() {
        List<Spending> list = new ArrayList<>();
        list = spendingRepository.findAll();
        return list;
    }

    /**
     * 전체 유저 select 후 userid와 일치하는 값만 list저장 후 리턴
     * 추후 userid만으로 select가 가능하다면 수정 할 예정
     * @param userid
     * @return
     */
    public List<Spending> getUserFindAll(int userid){
        List<Spending> list = new ArrayList<>();
        List<Spending> findUser = new ArrayList<>();
        list = spendingRepository.findAll();
        for (Spending spending :
                list) {
            if (spending.getUserid() == userid) {
                findUser.add(spending);
            }
        }
        return findUser;
    }

    @Transactional
    public Spending save(Spending spending) {
        spendingRepository.save(spending);
        return spending;
    }

    @Transactional(readOnly = true)
    public Spending findById(int userid) {
        return spendingRepository.findById(userid);
    }



}
