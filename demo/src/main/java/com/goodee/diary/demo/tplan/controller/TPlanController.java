package com.goodee.diary.demo.tplan.controller;

import com.goodee.diary.demo.tplan.domain.TPlan;
import com.goodee.diary.demo.tplan.domain.TPlanDetailVO;
import com.goodee.diary.demo.tplan.domain.WishList;
import com.goodee.diary.demo.tplan.service.TPlanCashService;
import com.goodee.diary.demo.tplan.service.TPlanDetailService;
import com.goodee.diary.demo.tplan.service.TPlanService;
import com.goodee.diary.demo.tplan.service.WishListService;
import com.goodee.diary.demo.user.domain.User;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Log4j2
@Controller
@RequestMapping(value = "/plan")
public class TPlanController {

    @Autowired
    private final TPlanService tPlanService;

//    @Autowired
//    TPlanDetailService tPlanDetailService;

    public TPlanController(TPlanService tPlanService) {
        this.tPlanService = tPlanService;
//        this.tPlanDetailService= tPlanDetailService;
    }

    @GetMapping
    public String diary(Model model){
        List<TPlan> userDiaryList = tPlanService.findAllById(1);
        model.addAttribute("userDiaryList", userDiaryList);
//        return userDiaryList.toString();
        return "plan/tPlan_list";

    }

    @PostMapping("/addplanlist")
    public String addlist(HttpServletRequest request) {
        log.info("dddddddddddddddddddd");
        log.info("tplanname : {}" , request.getParameter("tplanname"));
        log.info("startdate : {}" , request.getParameter("startdate"));
        log.info("enddate : {}" , request.getParameter("enddate"));
        log.info("money : " + request.getParameter("money"));

        String[] startDates = request.getParameter("startdate").split("-");
        String[] endDates = request.getParameter("enddate").split("-");
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        int year = Integer.parseInt(startDates[0]);
        int month = Integer.parseInt(startDates[1]) - 1;
        int monthOfDay = Integer.parseInt(startDates[2]);

        int year1 = Integer.parseInt(endDates[0]);
        int month1 = Integer.parseInt(endDates[1]) - 1;
        int monthOfDay1 = Integer.parseInt(endDates[2]);

        cal.set(year, month, monthOfDay);
        cal1.set(year1, month1, monthOfDay1);

        TPlan tplan1 = new TPlan(1, cal.getTime(), cal1.getTime(),
                request.getParameter("tplanname"),
                Integer.parseInt(request.getParameter("money")));

        TPlan newTplan = tPlanService.save(tplan1);
        log.info("newPlan = {}", newTplan.toString());
        return "redirect:/plan";
    }
//    @GetMapping
//    public String diary1(Model model){
//        List<TPlanDetailVO> scheduleList = tPlanDetailService.findAllById(1);
//        model.addAttribute("scheduleList", scheduleList);
////        return userDiaryList.toString();
//        return "plan/tPlan";
//
//    }
//
//    @PostMapping("/schedulerlist")
//    public String addlist1(HttpServletRequest request) {
//        log.info("dddddddddddddddddddd");
//        log.info("startdatedetail : {}" , request.getParameter("startdatedetail"));
//        log.info("contents1 : {}" , request.getParameter("contents1"));
//        log.info("memo : {}" , request.getParameter("memo"));
//
//        String[] startDatesDetail = request.getParameter("startdatedetail").split("-");
//        Calendar cal = Calendar.getInstance();
//        int year = Integer.parseInt(startDatesDetail[0]);
//        int month = Integer.parseInt(startDatesDetail[1]) - 1;
//        int monthOfDay = Integer.parseInt(startDatesDetail[2]);
//
//        cal.set(year, month, monthOfDay);
//
//        TPlanDetailVO TPlanDetail1 = new TPlanDetailVO(1, cal.getTime(),
//                request.getParameter("contents1"),
//                request.getParameter("memo"));
//
//        TPlanDetailVO newTPlanDetailVO = tPlanDetailService.save(TPlanDetail1);
//        log.info("newDPlan = {}", newTPlanDetailVO.toString());
//        return "redirect:/plan";
//    }

    @PostMapping("/post/edit/{id}")
    public String updateMethod(TPlan tPlan){
        log.info("TPlan : {}",tPlan.toString());

        return null;

    }



}

