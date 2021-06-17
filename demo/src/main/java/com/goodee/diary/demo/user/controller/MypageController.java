package com.goodee.diary.demo.user.controller;

import com.goodee.diary.demo.ScriptUtils;
import com.goodee.diary.demo.user.domain.User;
import com.goodee.diary.demo.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
@Controller
@RequestMapping("/mypage")
public class MypageController {
    @Autowired
    UserService userService;

    @GetMapping
    public String testMypage(HttpSession session, Model model) {
        User loginUser = (User) session.getAttribute("ConfirmUser");
        model.addAttribute("loginUser", loginUser);

        return "login/mypage";
    }



    @GetMapping("updateuser")
    public String mypageMethod(HttpServletRequest request, Model model) {
        log.info("mypageuser Test Start");
        HttpSession session = request.getSession();
        User loginUser = (User)session.getAttribute("ConfirmUser");
        model.addAttribute("loginUser", loginUser);

        return "login/updateuser";
    }

    @PostMapping("updateuser")
    public String mypagePostMethod(HttpServletRequest request, User user) throws IOException {
//        ScriptUtils.alert(response, "비밀번호를 변경했습니다 재 로그인 해주세요. ");
        User updateUser = userService.savePW(user.getUseremail(),
                user.getUsername(), user.getUserpw());
        log.info("updateUser = {}", updateUser.toString());
        return "redirect:/login";
    }
}
