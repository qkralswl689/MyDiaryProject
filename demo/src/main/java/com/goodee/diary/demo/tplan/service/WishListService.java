package com.goodee.diary.demo.tplan.service;

import com.goodee.diary.demo.tplan.dao.WishListRepository;
import com.goodee.diary.demo.tplan.domain.WishList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class WishListService {

    @Autowired
    WishListRepository wishListRepository;
    public List<WishList> findall(){
        List<WishList> list = new ArrayList<>();
        list = wishListRepository.findAll();
        return list;
    }

    @Transactional
    public WishList save(WishList wishList){
        wishListRepository.save(wishList);
        return wishList;
    }
}
