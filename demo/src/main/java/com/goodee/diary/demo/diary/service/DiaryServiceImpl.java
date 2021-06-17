package com.goodee.diary.demo.diary.service;



import com.goodee.diary.demo.diary.dao.DiaryDto;
import com.goodee.diary.demo.diary.dao.DiaryRepository;
import com.goodee.diary.demo.diary.domain.DiaryVO;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service // 서비스 계층임을 명시
@Log
public class DiaryServiceImpl implements DiaryService {

    private DiaryRepository diaryRepository;



    public DiaryServiceImpl(DiaryRepository diaryRepository){
        this.diaryRepository = diaryRepository;
    }

    @Override
    @Transactional
    public Long saveDiary(DiaryDto diaryDto) {
        return diaryRepository.save(diaryDto.toEntity()).getId();
    }

    @Override
    public List<DiaryDto> getDiaryList() {

        List<DiaryVO> diaryVOList = diaryRepository.findAll();

        List<DiaryDto> diaryDtoList = new ArrayList<>();

        for(DiaryVO diaryVo : diaryVOList){
            DiaryDto diaryDto = DiaryDto.builder()
                    .id(diaryVo.getId())
                    .title(diaryVo.getTitle())
                    .content(diaryVo.getContent())
                    .createdDate(diaryVo.getCreatedDate())
                    .ckshare(diaryVo.getCkshare())
                    .username(diaryVo.getUsername())
                    .build();
            diaryDtoList.add(diaryDto);
        }
        return diaryDtoList;
    }

    @Override
    @Transactional
    public List<DiaryDto> getDiaryListById(String username) {
        List<DiaryVO> diaryVOList = diaryRepository.findAll();

        List<DiaryDto> selectedList = new ArrayList<>();

        for (DiaryVO diaryVo :
                diaryVOList) {
            if (!(diaryVo.getUsername() == null) && diaryVo.getUsername().trim().equals(username)) {
                DiaryDto diaryDto = DiaryDto.builder()
                        .id(diaryVo.getId())
                        .title(diaryVo.getTitle())
                        .content(diaryVo.getContent())
                        .createdDate(diaryVo.getCreatedDate())
                        .ckshare(diaryVo.getCkshare())
                        .username(diaryVo.getUsername())
                        .build();
                selectedList.add(diaryDto);
            }
        }
        return selectedList;
    }


    @Override
    public List<DiaryDto> shareAllDiary() {

        List<DiaryVO> diaryVOList = diaryRepository.findAll();

        List<DiaryDto> diaryShareList = new ArrayList<>();

        for(DiaryVO diaryVo : diaryVOList){
            DiaryDto diaryDto = DiaryDto.builder()
                    .id(diaryVo.getId())
                    .title(diaryVo.getTitle())
                    .content(diaryVo.getContent())
                    .createdDate(diaryVo.getCreatedDate())
                    .ckshare(diaryVo.getCkshare())
                    .username(diaryVo.getUsername())
                    .build();
            if(diaryDto.getCkshare() != null){
                diaryShareList.add(diaryDto);
            }
        }
        return diaryShareList;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<DiaryVO> getAll() {
        List<DiaryVO> list = new ArrayList<>();
        list = diaryRepository.findAll();
        return list;
    }

    /**
     * 전체 유저 select 후 userid와 일치하는 값만 list저장 후 리턴
     * 추후 userid만으로 select가 가능하다면 수정 할 예정
     * @param username
     * @return
     */
    @Override
    public List<DiaryVO> getUserFindAll(String username) {
        List<DiaryVO> list = new ArrayList<>();
        List<DiaryVO> findUser = new ArrayList<>();
        list = diaryRepository.findAll();
        for (DiaryVO diaryVO :
                list) {
            if (diaryVO.getUsername() ==  username) {
                findUser.add(diaryVO);
            }
        }
        return findUser;
    }



    @Override
    public DiaryDto getDiary(Long id) {

        DiaryVO diaryVO = diaryRepository.findById(id).get();
        log.info("diaryVo : " + diaryVO);

        DiaryDto diaryDto = DiaryDto.builder()
                .id(diaryVO.getId())
                .title(diaryVO.getTitle())
                .content(diaryVO.getContent())
                .createdDate(diaryVO.getCreatedDate())
                .fileId(diaryVO.getFileId())
                .ckshare(diaryVO.getCkshare())
                .username(diaryVO.getUsername())
                .build();
        return diaryDto;
    }

    @Override
    @Transactional
    public void deleteDiary(Long id) {
        diaryRepository.deleteById(id);
    }


    @Override
    public int findId() {
        return diaryRepository.findId();
    }






}
