package org.team2project.camealone.controller;

import org.slf4j.Logger;
import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private HttpSession session;

    @Autowired
    private LoginService loginService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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
        Map<String, Object> response = new HashMap<>();
        String msg = loginService.register(memberDTO);
        if (msg.equals("success")) {
            response.put("success", true);
            response.put("message", "회원가입이 완료되었습니다");
        } else {
            response.put("success", false);
            response.put("message", msg);
        }
        return response;
    }

    @RequestMapping("/deleteuser")
    public String deleteUser() {
        return "deleteuser";
    }

    // 회원 탈퇴 기능 추가
    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteUser(@RequestBody Map<String, String> data) {
        String sessionId = (String) session.getAttribute("id");
        logger.debug("Session ID: {}", sessionId);

        Map<String, Object> response = new HashMap<>();

        if (sessionId == null) {
            response.put("success", false);
            response.put("message", "로그인이 필요합니다.");
            logger.warn("로그인이 필요합니다.");
            return ResponseEntity.status(403).body(response);
        }

        String password = data.get("password");
        logger.debug("Password: {}", password);

        boolean isDeleted = loginService.deleteUser(sessionId, password);
        logger.debug("Is Deleted: {}", isDeleted);

        if (isDeleted) {
            session.invalidate();
            response.put("success", true);
            response.put("message", "회원 탈퇴가 완료되었습니다.");
            logger.info("회원 탈퇴가 완료되었습니다.");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "비밀번호가 일치하지 않습니다.");
            logger.warn("비밀번호가 일치하지 않습니다.");
            return ResponseEntity.status(400).body(response);
        }
    }
}
