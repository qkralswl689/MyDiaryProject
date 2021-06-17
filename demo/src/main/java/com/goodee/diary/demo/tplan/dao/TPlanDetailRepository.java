package com.goodee.diary.demo.tplan.dao;

import com.goodee.diary.demo.tplan.domain.TPlanDetailVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TPlanDetailRepository extends JpaRepository<TPlanDetailVO, Integer> {

    TPlanDetailVO findById(int id);

    List<TPlanDetailVO> findAllById(Iterable<Integer> ids);
    List<TPlanDetailVO> findAll();

    TPlanDetailVO save(TPlanDetailVO tPlanDetailVO);
}
