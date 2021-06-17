package com.goodee.diary.demo.user.controller;

import com.goodee.diary.demo.SHA2;
import com.goodee.diary.demo.ScriptUtils;
import com.goodee.diary.demo.user.domain.User;
import com.goodee.diary.demo.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/login")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public String getUser(HttpServletRequest request, Model model) {
        System.out.println("testpage login");
        HttpSession session = request.getSession();
        model.addAttribute("user", new User());
        return "login/login.html";
    }

    @PostMapping
    public String loginSuccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("login Success test");
        HttpSession session = request.getSession();
        log.debug("session value = {}", session);
        List<User> userList = userService.getAll();
        for (User user :
                userList) {
//            System.out.println("userList : " + userList.toString());
//            System.out.println("request useremail : " + request.getParameter("loginuseremail"));
//            System.out.println("request userpw : " + SHA2.getSHA256(request.getParameter("loginuserpw")));
            String securityPW = SHA2.getSHA256(request.getParameter("loginuserpw"));
            if (user.getUseremail().equals(request.getParameter("loginuseremail")) &&
                    user.getUserpw().equals(securityPW)) {
                session.setAttribute("ConfirmUser", user);
                return "redirect:/list";
            }
        }
//        ScriptUtils.alert(response, "이메일이나 비밀번호가 맞지 않습니다.");
        return "redirect:/login";
    }

    @PostMapping("register")
//    @ResponseBody
    public String insertUser(HttpServletRequest request, @RequestParam("userimage") MultipartFile file, Model model) throws IOException, ParseException {
//        @Valid User valuser, Errors errors,
//        if (null != errors && errors.getErrorCount() > 0) {
//            model.addAttribute("testError", errors.getAllErrors());
//            return "redirect:/login";
//        }

        // 파일 이름을 업로드 한 날짜로 바꾸어서 저장할 것이다
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());

        // 프로젝트 폴더에 저장하기 위해 절대경로를 설정 (Window 의 Tomcat 은 Temp 파일을 이용한다)
        String absolutePath = new File("").getAbsolutePath() + "\\src\\main\\resources\\static\\";
        //C:\Users\misec\Desktop\demo + "\\src\\main\\resources\\static"

        // 경로를 지정하고 그곳에다가 저장할 심산이다
        String path = absolutePath + "images/";
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
        log.info("absolutePath = {}", absolutePath);
        log.info("path = {}", path);
        log.info("new_file_name = {}", new_file_name);
        log.info("testString = {}", testString);
        file.transferTo(file1);
        List<User> userList = userService.getAll();
        for (User user :
                userList) {
            if (user.getUseremail().equals(request.getParameter("useremail"))) {
                System.out.println("중복된 이메일");
                return null;
            }
        }
        String[] inserDateValue = request.getParameter("userbirth").split("-");
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(inserDateValue[0]);
        int month = Integer.parseInt(inserDateValue[1]) - 1;
        int monthOfDay = Integer.parseInt(inserDateValue[2]);
        cal.set(year, month, monthOfDay);
        User newUser = null;

        if (file.isEmpty()) {
            newUser = new User(request.getParameter("useremail"),
                    SHA2.getSHA256(request.getParameter("userpw")),
                    request.getParameter("username"),
                    cal.getTime());
        } else {
            newUser = new User(request.getParameter("useremail"),
                    SHA2.getSHA256(request.getParameter("userpw")),
                    request.getParameter("username"),
                    cal.getTime(),
                    new_file_name);
        }

        User savedUser = userService.save(newUser);
//        System.out.println(savedUser.toString());
        System.out.println(newUser.toString());

        return "redirect:/login";
//        return newUser.toString();
    }

    @PostMapping("findemail")
    public String findEmailMethod(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userid = request.getParameter("userid");

        List<User> userList = userService.getAll();
        for (User user :
                userList) {
            if (user.getUsername().equals(userid)) {
                ScriptUtils.alert(response, "아이디와 일치하는 이메일은 " + user.getUseremail() + " 입니다!");
                return "redirect:/login";
            }
        }
        ScriptUtils.alert(response,"아이디와 일치하는 이메일이 없습니다!");
        return "login/login";
    }

    @PostMapping("findPW")
    public String findPWMethod(HttpServletRequest request)  {
        log.info("findPW Method start");
        log.info("{}", request.getParameter("findid"));
        log.info("{}", request.getParameter("findemail"));
        User updateUser = userService.savePW(request.getParameter("findemail"),
                request.getParameter("findid"), "goodee1!");
        log.info("updateUser = {}", updateUser.toString());

//        ScriptUtils.alertAndBackPage(response, "goodee1!으로 비밀번호 변경 완료했습니다" );
        return "redirect:/login";
    }
}
