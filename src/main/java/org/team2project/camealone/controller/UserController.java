package org.team2project.camealone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.team2project.camealone.member.MemberDTO;
import org.team2project.camealone.service.LoginService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/loginService")
    @ResponseBody
    public Map<String, Object> loginService(@RequestBody Map<String, String> loginData) {
        String id = loginData.get("id");
        String password = loginData.get("password");

        String msg = loginService.loginService(id, password);
        Map<String, Object> response = new HashMap<>();
        if (msg.equals("success")) {
            response.put("success", true);
        } else {
            response.put("success", false);
            response.put("message", msg);
        }
        return response;
    }

    @GetMapping("/signup")
    public String signup() {
        return "Sign_up";
    }

    @PostMapping("/register")
    @ResponseBody
    public Map<String, Object> register(@RequestBody MemberDTO memberDTO) {
        String msg = loginService.register(memberDTO);
        Map<String, Object> response = new HashMap<>();
        if (msg.equals("success")) {
            response.put("success", true);
            response.put("message", "회원가입이 완료되었습니다, 3초후 자동으로 로그인 페이지로 이동합니다.");
        } else {
            response.put("success", false);
            response.put("message", msg);
        }
        return response;
    }
}
