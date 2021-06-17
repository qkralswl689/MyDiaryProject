package com.goodee.diary.demo.spending.dao;

import com.goodee.diary.demo.spending.domain.Minus;
import com.goodee.diary.demo.spending.domain.Spending;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MinusRepository  extends CrudRepository<Minus, Integer> {

    List<Minus> findAll();

//    //지출 저장
//    Minus minusSave(Minus minus);
//
//    //개인 지출 호출
//    Optional<Minus> findById(int userId);
//
//    //개인 지출 삭제(인덱스와 일치하는 개인지출내역 삭제
//    int deleteSpending(int spendingId);
//
//    //개인 지출 수정
//    Spending spendingUpdate(Spending spending);
}
