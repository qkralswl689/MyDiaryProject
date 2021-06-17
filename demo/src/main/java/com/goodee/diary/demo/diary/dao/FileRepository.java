package com.goodee.diary.demo.diary.dao;

import com.goodee.diary.demo.diary.domain.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface FileRepository extends JpaRepository<FileUpload,Long> {

    @Query(value = "SELECT MAX(id) FROM file_upload",nativeQuery = true )
    int findId();


    void deleteById(Long id);
}
