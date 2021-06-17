package com.goodee.diary.demo.diary.dao;

import com.goodee.diary.demo.diary.domain.DiaryVO;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class DiaryDto {

    /** 번호 (PK) */
    private Long id;

    /** 제목 */
    private String title;

    /** 내용 */
    private String content;

    /** 업로드 파일 */
    private Long fileId;


    /** 등록일 */
    private Date createdDate;

    /** 수정일 */
    private LocalDateTime modifiedDate;


    // 공유 컬럼 추가
    private String ckshare;

    // 유저네임
    private String username;

    public DiaryVO toEntity(){
        DiaryVO build = DiaryVO.builder()
                .id(id)
                .title(title)
                .content(content)
                .fileId(fileId)
                .ckshare(ckshare)
                .createdDate(createdDate)
                .username(username)
                .build();

        return build;
    }


    @Builder
    public DiaryDto(Long id, String title, String content,  Date createdDate, LocalDateTime modifiedDate, Long fileId,String ckshare,String username) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.fileId = fileId;
        this.ckshare = ckshare;
        this.username =username;
    }


}
