package com.goodee.diary.demo.diary.service;


import com.goodee.diary.demo.diary.dao.DiaryFileDto;

import java.util.List;

public interface FileService {

    public Long saveFile(DiaryFileDto diaryFileDto);

    public DiaryFileDto getFile(Long id);

    // 파일 삭제
    public void deleteFile(Long id);

    public int findId();


    // 저장 목록
    public List<DiaryFileDto> getFileList();

}
