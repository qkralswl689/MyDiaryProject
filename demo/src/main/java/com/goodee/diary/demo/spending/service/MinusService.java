package com.goodee.diary.demo.spending.service;

import com.goodee.diary.demo.spending.dao.MinusRepository;
import com.goodee.diary.demo.spending.dao.SpendingRepository;
import com.goodee.diary.demo.spending.domain.Minus;
import com.goodee.diary.demo.spending.domain.Spending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MinusService {
    @Autowired
    MinusRepository minusRepository;

    @Transactional(readOnly = true)
    public List<Minus> getAll(){
        List<Minus> list = new ArrayList<>();
        list = (List<Minus>) minusRepository.findAll();
        return list;
    }

    /**
     * 전체 유저 select 후 userid와 일치하는 값만 list저장 후 리턴
     * 추후 userid만으로 select가 가능하다면 수정 할 예정
     * @param userid
     * @return
     */
    public List<Minus> getUserFindAll(int userid){
        List<Minus> list = new ArrayList<>();
        List<Minus> findUser = new ArrayList<>();
        list = minusRepository.findAll();
        for (Minus minus :
                list) {
            if (minus.getUserid() == userid) {
                findUser.add(minus);
            }
        }
        return findUser;
    }

    @Transactional
    public Minus save(Minus minus) {
        minusRepository.save(minus);
        return minus;
    }
}
