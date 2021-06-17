package com.goodee.diary.demo.tplan.dao;

import com.goodee.diary.demo.tplan.domain.TPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TplanRepository extends JpaRepository<TPlan,Integer> {

    TPlan findById(int tplanid);

    List<TPlan> findAllById(Iterable<Integer> ids);

    List<TPlan> findAll();

    TPlan save(TPlan tPlan);

}
