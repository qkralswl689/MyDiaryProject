package com.goodee.diary.demo.spending.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "expenditure")
@SequenceGenerator(
        name="MINUS_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName="minus_seq", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)
public class Minus {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "MINUS_SEQ_GEN"
    )
    private int minusid;

    private int userid;

    private Date minusdate;

    private String minusname;

    private String iscard;

    //지출 내역
    private int minusmoney;

}
