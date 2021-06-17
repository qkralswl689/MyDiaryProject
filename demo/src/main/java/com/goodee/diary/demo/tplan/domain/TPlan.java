package com.goodee.diary.demo.tplan.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;



@Entity
@Data
@Table(name = "tplan")
@SequenceGenerator(
        name = "tplan_seq_gen",
        sequenceName = "tplan_seq",
        initialValue = 1,
        allocationSize = 1
)
public class TPlan {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tplan_seq_gen"
    )
    private int tplanId;
    private int userId;
    private Date tplanStartDate;
    private Date tplanEndDate;
    private String tplanName;
    private int tplanBudget;

    public TPlan(){

    }

    public TPlan(int userid, Date tplanStartDate, Date tplanEndDate, String tplanName, int tplanBudget) {
        this.userId = userid;
        this.tplanStartDate = tplanStartDate;
        this.tplanEndDate = tplanEndDate;
        this.tplanName = tplanName;
        this.tplanBudget = tplanBudget;
    }
}
