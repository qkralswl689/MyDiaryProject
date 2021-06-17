package com.goodee.diary.demo.diary.controller;

import com.goodee.diary.demo.diary.dao.DiaryDto;
import com.goodee.diary.demo.diary.dao.DiaryFileDto;
import com.goodee.diary.demo.diary.domain.DiaryVO;
import com.goodee.diary.demo.diary.service.DiaryServiceImpl;
import com.goodee.diary.demo.diary.service.FileServiceImpl;
import com.goodee.diary.demo.user.domain.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/")
@Log4j2
public class DiaryController {

    private DiaryServiceImpl diaryService;
    private FileServiceImpl fileService;

    public DiaryController(DiaryServiceImpl diaryService, FileServiceImpl fileService) {
        this.diaryService = diaryService;
        this.fileService = fileService;
    }

    @GetMapping
    public String hello(Model model){
        return "index";

    }

    @GetMapping("diary")

    public String diary(HttpSession session, Model model){

        log.info("Session user = {}", session.getAttribute("ConfirmUser"));

        User loginUser = (User)session.getAttribute("ConfirmUser");
        model.addAttribute("loginUser", loginUser);

        return "diary/diaryWrite";

    }

    @PostMapping("/post")
    public String write(@RequestParam("file") MultipartFile file, DiaryDto diaryDto ,HttpServletRequest request,HttpSession session,Model model) throws IOException {


        //세션에 저장된 유저 값을 불러온다.
        User sessionUser = (User)session.getAttribute("ConfirmUser");

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");

        String[] inserDateValue = request.getParameter("writedate").split("-");
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(inserDateValue[0]);
        int month = Integer.parseInt(inserDateValue[1]) - 1;
        int monthOfDay = Integer.parseInt(inserDateValue[2]);
        cal.set(year, month, monthOfDay);

        // 파일 이름을 업로드 한 날짜로 바꾸어서 저장할 것이다
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());
        String origFilename = file.getOriginalFilename();

        // 프로젝트 폴더에 저장하기 위해 절대경로를 설정 (Window 의 Tomcat 은 Temp 파일을 이용한다)

        String absolutePath = "C:\\Users\\82104\\Desktop\\project\\";
        log.info("absolutePath" + new File("").getAbsolutePath());

        // 경로를 지정하고 그곳에다가 저장할 심산이다
        String path = absolutePath + "diaryimg/";
        File file1 = new File(path);
        // 저장할 위치의 디렉토리가 존지하지 않을 경우
        if (!file1.exists()) {
            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
            file1.mkdirs();
        }

        // 파일들을 이제 만져볼 것이다
        // 파일이 비어 있지 않을 때 작업을 시작해야 오류가 나지 않는다
        // jpeg, png, gif 파일들만 받아서 처리할 예정
        String contentType = file.getContentType();
        String originalFileExtension = null;
        // 확장자 명이 없으면 이 파일은 잘 못 된 것이다
        if (contentType.contains("image/jpeg")) {
            originalFileExtension = ".jpg";
        } else if (contentType.contains("image/png")) {
            originalFileExtension = ".png";
        } else if (contentType.contains("image/gif")) {
            originalFileExtension = ".gif";
        }

        // 다른 파일 명이면 아무 일 하지 않는다
        // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
        String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;
        // 생성 후 리스트에 추가

        // 저장된 파일로 변경하여 이를 보여주기 위함
        String testString = path + new_file_name;
        file1 = new File(testString);
        log.info("absolutePath = "+ absolutePath);
        log.info("path = "+ origFilename);
        log.info("new_file_name = "+ new_file_name);
        log.info("testString = "+ testString);
        file.transferTo(file1);
        log.info("####postFile");
        DiaryFileDto diaryFileDto = new DiaryFileDto();

        diaryFileDto.setOrigFilename(origFilename);
        diaryFileDto.setFilename(new_file_name);
        diaryFileDto.setFilePath(path);

        log.info("#########################################postFile : " + diaryFileDto);
        Long fileId = fileService.saveFile(diaryFileDto);
        diaryDto.setFileId(fileId);
        diaryFileDto.setId(diaryDto.getFileId());
        diaryDto.setCreatedDate(cal.getTime());
        diaryDto.setUsername(sessionUser.getUsername());

        diaryService.saveDiary(diaryDto);
        log.info("postFile : " + diaryFileDto.getFilename());

        return "redirect:/list";
    }


    @GetMapping("/read/{id}")
    public String read(@PathVariable("id") Long id, Model model,HttpSession session) {

     /*   DiaryVO sessionDiary = (DiaryVO)session.getAttribute("diary");
        model.addAttribute("diary",sessionDiary);*/

        //세션에 저장된 유저 값을 불러온다.
        User loginUser = (User)session.getAttribute("ConfirmUser");
        model.addAttribute("loginUser", loginUser);

        DiaryDto diaryDto = diaryService.getDiary(id);

        DiaryFileDto diaryFileDto = new DiaryFileDto();

        diaryFileDto.setId(diaryDto.getFileId());
        Long fileId = diaryDto.getFileId();

        DiaryFileDto fileList = fileService.getFile(fileId);

        diaryFileDto.setFilename(fileList.getFilename());
        diaryFileDto.setOrigFilename(fileList.getOrigFilename());
        diaryFileDto.setFilePath(fileList.getFilePath());


        model.addAttribute("post", diaryDto);
        model.addAttribute("postFile",fileList);

        return "diary/diaryRead";
    }
    @GetMapping("/share/{id}")
    public String shareread(@PathVariable("id") Long id, Model model,HttpSession session) {

        User loginUser = (User)session.getAttribute("ConfirmUser");
        model.addAttribute("loginUser", loginUser);

        DiaryDto diaryDto = diaryService.getDiary(id);

        DiaryFileDto diaryFileDto = new DiaryFileDto();

        diaryFileDto.setId(diaryDto.getFileId());
        Long fileId = diaryDto.getFileId();

        DiaryFileDto fileList = fileService.getFile(fileId);

        diaryFileDto.setFilename(fileList.getFilename());
        diaryFileDto.setOrigFilename(fileList.getOrigFilename());
        diaryFileDto.setFilePath(fileList.getFilePath());


        model.addAttribute("post", diaryDto);
        model.addAttribute("postFile",fileList);

        return "diary/shareDiaryRead";
    }


    @GetMapping("/list")
    public String list(Model model,HttpSession session) {

        User loginUser = (User)session.getAttribute("ConfirmUser");
        model.addAttribute("loginUser", loginUser);

        List<DiaryDto> diaryDtoList = diaryService.getDiaryListById(loginUser.getUsername());
        List<DiaryFileDto> diaryFileDtoList = fileService.getFileList();
        model.addAttribute("postList", diaryDtoList);
        model.addAttribute("fileList",diaryFileDtoList);

        return "diary/myDiaryList";
    }

    @GetMapping("/sharelist")
    public String goShare(Model model,HttpSession session) {

            User loginUser = (User)session.getAttribute("ConfirmUser");
            model.addAttribute("loginUser", loginUser);

            List<DiaryDto> diaryShareList = diaryService.shareAllDiary();

            List<DiaryFileDto> diaryFileDtoList = fileService.getFileList();
            model.addAttribute("shareList", diaryShareList);
            model.addAttribute("fileList",diaryFileDtoList);

        return "diary/shareDiaryList";
    }

    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id,Model model,HttpSession session) {

        User loginUser = (User)session.getAttribute("ConfirmUser");
        model.addAttribute("loginUser", loginUser);
        DiaryDto diaryDto = diaryService.getDiary(id);

        DiaryFileDto diaryFileDto = fileService.getFile(diaryDto.getFileId());


        model.addAttribute("postFile",diaryFileDto);
        model.addAttribute("post", diaryDto);
        /* return "board/edit.html";*/
        return "diary/diaryUpdate";
    }


    @PostMapping("/post/edit/{id}")
    /*@PutMapping("/post/edit/{id}")*/
    public String update(@RequestParam("file") MultipartFile file,HttpSession session,DiaryDto diaryDto,DiaryFileDto diaryFileDto,Model model)throws IOException
    {

        //세션에 저장된 유저 값을 불러온다.
        User sessionUser = (User)session.getAttribute("ConfirmUser");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());
        String origFilename = file.getOriginalFilename();

        // 프로젝트 폴더에 저장하기 위해 절대경로를 설정 (Window 의 Tomcat 은 Temp 파일을 이용한다)
        /* String absolutePath = new File("").getAbsolutePath() + "\\demo\\src\\main\\resources\\static\\";*/
        String absolutePath = "C:\\Users\\82104\\Desktop\\project\\";
        log.info("absolutePath" + new File("").getAbsolutePath());
        //C:\Users\misec\Desktop\demo + "\\src\\main\\resources\\static"

        // 경로를 지정하고 그곳에다가 저장할 심산이다
        String path = absolutePath + "diaryimg/";
        File file1 = new File(path);
        // 저장할 위치의 디렉토리가 존지하지 않을 경우
        if (!file1.exists()) {
            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
            file1.mkdirs();
        }

        // 파일들을 이제 만져볼 것이다
        // 파일이 비어 있지 않을 때 작업을 시작해야 오류가 나지 않는다
        // jpeg, png, gif 파일들만 받아서 처리할 예정
        String contentType = file.getContentType();
        String originalFileExtension = null;
        // 확장자 명이 없으면 이 파일은 잘 못 된 것이다
        if (contentType.contains("image/jpeg")) {
            originalFileExtension = ".jpg";
        } else if (contentType.contains("image/png")) {
            originalFileExtension = ".png";
        } else if (contentType.contains("image/gif")) {
            originalFileExtension = ".gif";
        }

        // 다른 파일 명이면 아무 일 하지 않는다
        // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
        String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;
        // 생성 후 리스트에 추가

        // 저장된 파일로 변경하여 이를 보여주기 위함
        String testString = path + new_file_name;
        file1 = new File(testString);
        log.info("absolutePath = "+ absolutePath);
        log.info("path = "+ origFilename);
        log.info("new_file_name = "+ new_file_name);
        log.info("testString = "+ testString);

        file.transferTo(file1);

        diaryFileDto.setId(diaryDto.getFileId());
        diaryFileDto.setOrigFilename(origFilename);
        diaryFileDto.setFilename(new_file_name);
        diaryFileDto.setFilePath(path);

        log.info("#########################################postFile : " + diaryFileDto);
        Long fileId = fileService.saveFile(diaryFileDto);
        diaryDto.setFileId(fileId);
        diaryFileDto.setId(diaryDto.getFileId());
        diaryDto.setUsername(sessionUser.getUsername());
        diaryService.saveDiary(diaryDto);


        return "redirect:/list";


    }

    @PostMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id){
        log.info("//////////////////////////delete : " );
        Long fileId = diaryService.getDiary(id).getFileId();
        DiaryFileDto fileList = fileService.getFile(fileId);
        String testString = fileList.getFilePath() + fileList.getFilename();
        diaryService.deleteDiary(id);
        log.info("fileid : " + fileId);
        fileService.deleteFile(fileId);

        File file2 = new File(testString);
        if( file2.exists() ){
            if(file2.delete()){
                System.out.println("파일삭제 성공");
            }else{
                System.out.println("파일삭제 실패"); }
        }else{
            System.out.println("파일이 존재하지 않습니다.");
        }
        return  "redirect:/list" ;
    }

    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model ,HttpSession session){

        User loginUser = (User)session.getAttribute("ConfirmUser");
        model.addAttribute("loginUser", loginUser);

        DiaryDto diaryDto = diaryService.getDiary(id);
        model.addAttribute("post", diaryDto);

        return  "diary/diaryRead" ;
    }


}