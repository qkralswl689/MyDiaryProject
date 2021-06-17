package com.goodee.diary.demo.spending.dao;


import com.goodee.diary.demo.spending.domain.Spending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpendingRepository extends JpaRepository<Spending, Integer> {

    Spending findById(int userid);

    List<Spending> findAllById(Iterable<Integer> ids);

    List<Spending> findAll();

    Spending save(Spending spending);

    //    //고정 지출 저장
//    Spending spendingSave(Spending spending);

    //개인 고정 지출 호출
//    Optional<Spending> findById(int userId);

//    //고정 지출 삭제(인덱스와 일치하는 고정지출내역 삭제
//    int deleteSpending(int spendingId);
//
//    //고정 지출 수정
//    Spending spendingUpdate(Spending spending);


}
