package com.goodee.diary.demo.spending.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "spending")
@SequenceGenerator(
        name="SPENDING_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName="spending_seq", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)
public class Spending {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SPENDING_SEQ_GEN"
    )
    //고정 지출 인덱스
    private int spendingid;

    private int userid;

    private Date spenddate;

    private String spendname;

    //고정 지출 내역
    private int spending;

}
