package com.goodee.diary.demo.tplan.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "wishlist")
@SequenceGenerator(
        name = "wishlist_seq_gen",
        sequenceName = "wishlist_seq",
        initialValue = 1,
        allocationSize = 1
)
public class WishList {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "wishlist_seq_gen"
    )
    private int id;
    private int tplanId;
    private String wishList;

    public WishList(){

    }

    public WishList(int tplanID, String wishList) {
        this.tplanId = tplanID;
        this.wishList = wishList;
    }


}
