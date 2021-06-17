package com.goodee.diary.demo.spending.controller;

import com.goodee.diary.demo.spending.domain.*;
import com.goodee.diary.demo.spending.service.MinusService;
import com.goodee.diary.demo.spending.service.PlusService;
import com.goodee.diary.demo.spending.service.SpendingService;
import com.goodee.diary.demo.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/spending")
public class SpendingController {

    @Autowired
    SpendingService spendingService;
    @Autowired
    MinusService minusService;
    @Autowired
    PlusService plusService;

    public String testMethod(HttpServletRequest request, HttpServletResponse response){
        return null;

    }

//    @ResponseBody
    @GetMapping //, produces = "text/plain; charset=UTF-8"
    public String spendingTest(Model model, HttpSession session) {
        log.info("Session user = {}", session.getAttribute("ConfirmUser"));
        log.info("spending Test Start");

        User loginUser = (User)session.getAttribute("ConfirmUser");
        model.addAttribute("loginUser", loginUser);

        List<Spending> spending = spendingService.getUserFindAll(loginUser.getUserid());
        List<Minus> minusList = minusService.getUserFindAll(loginUser.getUserid());
        List<Plus> plusList = plusService.getUserFindAll(loginUser.getUserid());
        int plusSum = 0;
        for (Plus plusvalue :
              plusList  ) {
            plusSum += plusvalue.getPlusmoney();
        }

        int minusSum = 0;
        int cardSum = 0;
        int moneySum = 0;
        for (Minus minusvalue :
                minusList  ) {
            minusSum += minusvalue.getMinusmoney();
            if (minusvalue.getIscard().equals("card")){
                cardSum += minusvalue.getMinusmoney();
            }else {
                moneySum += minusvalue.getMinusmoney();
            }
        }

        int spendingSum = 0;
        for (Spending spendingValue :
                spending  ) {
            spendingSum += spendingValue.getSpending();
        }

        log.info("고정지출 : {}", spendingSum);
        log.info("수입 : {}", plusSum);
        log.info("지출 : {}", minusSum);
        log.info("카드값 : {}", cardSum);
        log.info("현금값 : {}",moneySum);
        int totalValue = plusSum - (spendingSum + minusSum);
        log.info("예산 : {}", totalValue);



        model.addAttribute("spendingList", spending);
        model.addAttribute("minusList", minusList);
        model.addAttribute("plusList", plusList);

        model.addAttribute("totalValue", totalValue);
        model.addAttribute("cardValue", cardSum);
        model.addAttribute("moneyValue", moneySum);
        model.addAttribute("minusValue", minusSum);

        return "spending/home";
    }

    @PostMapping("/save")
//    @ResponseBody
    public String saveValue(HttpServletRequest request, HttpSession session){
        //넘어오는 값을 로그로 확인한다.
        log.debug("spending, expenditure, plus = {}", request.getParameter("spendingvalue"));
        log.debug("spendingname = {}", request.getParameter("spendingname"));
        log.debug("spendingmoney = {}", request.getParameter("spendingmoney"));
        log.debug("spendingdate = {}", request.getParameter("spendingdate"));
        log.debug("iscard = {}", request.getParameter("iscard"));
        Map<String, String[]> parameterMap = request.getParameterMap();

        //세션에 저장된 유저 값을 불러온다.
        User sessionUser = (User)session.getAttribute("ConfirmUser");

        //date format ex) 2021-06-08
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");

        String spendingvalue = request.getParameter("spendingvalue");
        String[] inserDateValue = request.getParameter("spendingdate").split("-");
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(inserDateValue[0]);
        int month = Integer.parseInt(inserDateValue[1]) - 1;
        int monthOfDay = Integer.parseInt(inserDateValue[2]);
        cal.set(year, month, monthOfDay);

        if (spendingvalue.equals("expenditure")) {  //지출 선택
            log.info("expenditure value start");
            Minus testMinus = new Minus();
            try {
                testMinus.setUserid(sessionUser.getUserid());
                testMinus.setMinusdate(cal.getTime());
                testMinus.setMinusname(request.getParameter("spendingname"));
                testMinus.setIscard(request.getParameter("iscard"));
                testMinus.setMinusmoney(Integer.parseInt(request.getParameter("spendingmoney")));
                Minus newMinus = minusService.save(testMinus);
                log.info("save expenditure = "+ newMinus.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (spendingvalue.equals("plus")) {  //수입 선택
            log.info("plus value start");
            Plus testPlus = new Plus();

            try {
                testPlus.setUserid(sessionUser.getUserid());
                testPlus.setPlusdate(cal.getTime());
                testPlus.setPlusname(request.getParameter("spendingname"));
                testPlus.setPlusmoney(Integer.parseInt(request.getParameter("spendingmoney")));
                Plus newPlus = plusService.save(testPlus);
                log.info("save plus = "+ newPlus.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (spendingvalue.equals("spending")) {  //고정 지출 선택
            log.info("spending value start");
            Spending testSpending = new Spending();

            try {
                testSpending.setUserid(sessionUser.getUserid());
                testSpending.setSpenddate(cal.getTime());
                testSpending.setSpendname(request.getParameter("spendingname"));
                testSpending.setSpending(Integer.parseInt(request.getParameter("spendingmoney")));
                Spending newSpending = spendingService.save(testSpending);
                log.info("save spending = "+ newSpending.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // redirect로 재호출하면 넣은 데이터를 확인 할 수 있다.
        return "redirect:/spending";
    }
}
