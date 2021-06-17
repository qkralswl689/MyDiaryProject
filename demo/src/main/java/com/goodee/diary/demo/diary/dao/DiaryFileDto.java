package com.goodee.diary.demo.diary.dao;

import com.goodee.diary.demo.diary.domain.FileUpload;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Getter
@Setter
public class DiaryFileDto {

    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;

    public DiaryFileDto() {

    }

    public DiaryFileDto(String origFilename, String filename, String filePath) {
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }

    public FileUpload toEntity() {
        FileUpload build = FileUpload.builder()
                .id(id)
                .origFilename(origFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
        return build;
    }

    @Builder
    public DiaryFileDto(Long id, String origFilename, String filename, String filePath) {
        this.id = id;
        this.origFilename = origFilename;
        this.filename = filename;
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "DiaryFileDto{" +
                "id=" + id +
                ", origFilename='" + origFilename + '\'' +
                ", filename='" + filename + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
    public String getImageURL(){
        try {
            return URLEncoder.encode(filePath+"/"+origFilename+"_"+filename,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getThumbnailURL(){
        try {
            return URLEncoder.encode(filePath+"/s_"+origFilename+"_"+filename,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
