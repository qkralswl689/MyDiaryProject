package com.goodee.diary.demo.diary.dao;

import com.goodee.diary.demo.diary.domain.DiaryVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<DiaryVO, Long>, QuerydslPredicateExecutor<DiaryVO> {


    Optional<DiaryVO> findById(Long id);

    @Query(value = "SELECT MAX(id) FROM diaryvo",nativeQuery = true )
    int findId();

    List<DiaryVO> findAll();
/*    public List<DiaryVO> shareAllDiary();*/
}
