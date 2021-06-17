package com.goodee.diary.demo.spending.service;

import com.goodee.diary.demo.spending.dao.PlusRepository;
import com.goodee.diary.demo.spending.dao.SpendingRepository;
import com.goodee.diary.demo.spending.domain.Plus;
import com.goodee.diary.demo.spending.domain.Spending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlusService {
    @Autowired
    PlusRepository plusRepository;

    @Transactional(readOnly = true)
    public List<Plus> getAll(){
        List<Plus> list = new ArrayList<>();
        list = (List<Plus>) plusRepository.findAll();
        return list;
    }

    /**
     * 전체 유저 select 후 userid와 일치하는 값만 list저장 후 리턴
     * 추후 userid만으로 select가 가능하다면 수정 할 예정
     * @param userid
     * @return
     */
    public List<Plus> getUserFindAll(int userid){
        List<Plus> list = new ArrayList<>();
        List<Plus> findUser = new ArrayList<>();
        list = plusRepository.findAll();
        for (Plus plus :
                list) {
            if (plus.getUserid() == userid) {
                findUser.add(plus);
            }
        }
        return findUser;
    }

    @Transactional
    public Plus save(Plus plus) {
        plusRepository.save(plus);
        return plus;
    }
}
