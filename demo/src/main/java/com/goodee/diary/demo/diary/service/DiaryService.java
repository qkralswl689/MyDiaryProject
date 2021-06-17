package com.goodee.diary.demo.diary.service;


import com.goodee.diary.demo.diary.dao.DiaryDto;
import com.goodee.diary.demo.diary.domain.DiaryVO;

import java.util.List;

// 다이어리 관련 작업을 위한 메소드 소유 인터페이스
public interface DiaryService {

    // 저장
    public Long saveDiary(DiaryDto diaryDto);


    // 저장 목록
    public List<DiaryDto> getDiaryList();

    // 다이어리 불러오기
    public DiaryDto getDiary(Long id);

    // 다이어리 삭제
    public void deleteDiary(Long id);

    // 공유 다이어리
    public List<DiaryDto> shareAllDiary();

    // 유저 가져오기
    public List<DiaryVO> getAll();

    public List<DiaryVO> getUserFindAll(String useremail);

    public List<DiaryDto> getDiaryListById(String username);

    int findId();


}
