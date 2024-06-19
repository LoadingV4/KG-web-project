package org.team2project.camealone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VariousController {

    @GetMapping("/date") //  날짜 선택 페이지
    public String getDate(){return "Date_select";}

    @GetMapping("/Restaurant_Weather")
    public String Restaurant() {
        return "Restaurant_Weather";
    }

    @GetMapping("/MyPage")
    public String MyPage() {return "MyPage";}

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

    @GetMapping("/Info")
    public String Info() {return "Info";}

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/Like")
    public String Like() {return "like";}
}
