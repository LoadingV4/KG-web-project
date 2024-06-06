package org.team2project.camealone.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VariousController {
    @GetMapping("/date") //  날짜 선택 페이지
    public String getDate(){
        return "Date_select";
    }

    @GetMapping("/Restaurant_Weather")
    public String Restaurant() {
        return "Restaurant_Weather";
    }

    @GetMapping("/areaDropdown")
    public String AreaDropdown() {
        return "AreaDropdown";
    }

    @GetMapping("/MyPage")
    public String MyPage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/user/login";
        }else {
            return "MyPage";
        }
    }

    @GetMapping("/list")
    public String List() {
        return "list";
    }

    @GetMapping("/Hotel")
    public String Hotel() {
        return "Hotel";
    }

    @GetMapping("/Tour_att")
    public String TourAtt() {
        return "Tour_att";
    }
}
