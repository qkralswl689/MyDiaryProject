package com.goodee.diary.demo.diary.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class) /* JPA에게 해당 Entity는 Auditiong 기능을 사용함을 알린다. */
@SequenceGenerator(
        name="DIARY_SEQ_GEN", //시퀀스 제너레이터 이름
        sequenceName="diary_seq", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)
public class DiaryVO /*implements Serializable*/ {

    /** 번호 (PK) */
    @Id
    @GeneratedValue
    private Long id;

    /** 제목 */
    @NotNull(message = "제목을 입력하세요 ")
    @Column(length = 100, nullable = false)
    private String title;

    /** 내용 */
    @NotNull(message = "다이어리 내용을 입력하세요 ")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    /** 업로드 파일 */
    @Column
    private Long fileId;

    /** 등록일 */
    @CreatedDate
    @Column(updatable = false)
    private Date createdDate;

    /** 수정일 */
    @LastModifiedDate
    private LocalDateTime modifiedDate;

    // 공유 컬럼
    @Column
    private String ckshare;

    // 유저이름
    @Column
    private String username;

    public DiaryVO() {
    }

    @Builder
    public DiaryVO(Long id, String title, String content, Long fileId, String ckshare,Date createdDate,String username){
        this.id = id;
        this.title = title;
        this.content = content;
        this.fileId = fileId;
        this.ckshare = ckshare;
        this.createdDate = createdDate;
        this.username = username;
    }

}
