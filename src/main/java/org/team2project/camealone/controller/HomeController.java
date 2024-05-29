package org.team2project.camealone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/") //  메인페이지
    public String home() {
        return "Main";
    }
}
