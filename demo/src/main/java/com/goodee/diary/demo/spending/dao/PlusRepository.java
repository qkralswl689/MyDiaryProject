package com.goodee.diary.demo.spending.dao;

import com.goodee.diary.demo.spending.domain.Plus;
import com.goodee.diary.demo.spending.domain.Spending;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlusRepository extends CrudRepository<Plus, Integer> {

    List<Plus> findAll();

//    //수입 저장
//    Plus plusSave(Plus plus);
//
//    //개인 수입 호출
//    Optional<Plus> findById(int userId);

}
