package com.goodee.diary.demo.tplan.dao;

import com.goodee.diary.demo.tplan.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList,Integer> {

    List<WishList> findAll();

    WishList save(WishList wishList);

}
