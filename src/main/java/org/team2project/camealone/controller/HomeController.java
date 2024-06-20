package org.team2project.camealone.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {
    @GetMapping("/") //  메인페이지
    public String home() {
        return "Main";
    }

    // MIME 타입 설정 코드
    @GetMapping("/css/Main.css")
    @ResponseBody
    public void getCss(HttpServletResponse response) throws IOException {
        Path path = Paths.get("src/main/resources/static/css/Main.css");
        response.setContentType("text/css");
        Files.copy(path, response.getOutputStream());
    }

    // 로그인 여부를 확인해서 로그인이 된 상태에서만 마이페이지로 이동하게하는 코드
    // response가 response.json을 반환하면 로그인이 된것
    @GetMapping("/api/check-login-status")
    public ResponseEntity<Map<String, Boolean>> checkLoginStatus(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        boolean loggedIn = (session != null && session.getAttribute("user") != null);
        Map<String, Boolean> response = new HashMap<>();
        response.put("loggedIn", loggedIn);
        return ResponseEntity.ok(response);
    }
}
