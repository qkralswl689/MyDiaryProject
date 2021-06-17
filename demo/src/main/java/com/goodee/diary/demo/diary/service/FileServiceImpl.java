package com.goodee.diary.demo.diary.service;

import com.goodee.diary.demo.diary.dao.DiaryFileDto;
import com.goodee.diary.demo.diary.dao.FileRepository;
import com.goodee.diary.demo.diary.domain.FileUpload;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Log
public class FileServiceImpl implements FileService {

    private FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository){
        this.fileRepository = fileRepository;
    }


    @Override
    @Transactional
    public Long saveFile(DiaryFileDto diaryFileDto) {
        return fileRepository.save(diaryFileDto.toEntity()).getId();
    }

    @Override
    @Transactional
    public DiaryFileDto getFile(Long id) {

        log.info("@@@@@@@@@@@@@@@@@@@@@@");
        FileUpload fileUpload = fileRepository.findById(id).get();

        log.info("fileUpload : " + fileUpload);

        DiaryFileDto diaryFileDto = DiaryFileDto.builder()
                .id(id)
                .origFilename(fileUpload.getOrigFilename())
                .filename(fileUpload.getFilename())
                .filePath(fileUpload.getFilePath())
                .build();

        return diaryFileDto;
    }

    @Override
    @Transactional
    public void deleteFile(Long id) {
        fileRepository.deleteById(id);
    }

    @Override
    public int findId() {
        return fileRepository.findId();
    }

    @Override
    public List<DiaryFileDto> getFileList() {
        List<FileUpload> FileVOList = fileRepository.findAll();
        List<DiaryFileDto> diaryFileDtoList = new ArrayList<>();

        for(FileUpload fileUpload : FileVOList){
            DiaryFileDto diaryFileDto = DiaryFileDto.builder()
                    .id(fileUpload.getId())
                    .origFilename(fileUpload.getOrigFilename())
                    .filename(fileUpload.getFilename())
                    .filePath(fileUpload.getFilePath())
                    .build();
            diaryFileDtoList.add(diaryFileDto);
        }
        return diaryFileDtoList;
    }



}
