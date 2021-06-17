package com.goodee.diary.demo.tplan.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "tplan_c")
@SequenceGenerator(
        name = "tplanc_seq_gen",
        sequenceName = "tplanc_seq",
        initialValue = 1,
        allocationSize = 1
)
public class TPlanCash {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tplanc_seq_gen"
    )
    private int id;
    private int tplanId;
    private String tPlanCContents;
    private int tPlanCCost;
    private String tPlanCCategory;

    public TPlanCash(){

    }

    public TPlanCash(int tplanID, String tPlanCContents, int tPlanCCost, String tPlanCCategory) {
        this.tplanId = tplanID;
        this.tPlanCContents = tPlanCContents;
        this.tPlanCCost = tPlanCCost;
        this.tPlanCCategory = tPlanCCategory;
    }

}
