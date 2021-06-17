package com.goodee.diary.demo.tplan.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "tplan_d")
@SequenceGenerator(
        name = "tpland_seq-gen",
        sequenceName = "tpland_seq",
        initialValue = 1,
        allocationSize = 1
)
public class TPlanDetailVO {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tpland_seq-gen"
    )
    private int id;
    private int tplanID;
    private Date start_D;
    private String contents;
    private String memo;

    public TPlanDetailVO(){

    }

    public TPlanDetailVO(int tplanID, Date start_D,  String contents, String memo) {
        this.tplanID = tplanID;
        this.start_D = start_D;
        this.contents = contents;
        this.memo = memo;
    }

}
