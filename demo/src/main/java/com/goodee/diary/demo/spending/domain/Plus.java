package com.goodee.diary.demo.spending.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "plus")
@SequenceGenerator(
        name="PLUS_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName="plus_seq", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)
public class Plus {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "PLUS_SEQ_GEN"
    )
    private int plusid;

    private int userid;

    private Date plusdate;

    private String plusname;

    //수입 내역
    private int plusmoney;

}
