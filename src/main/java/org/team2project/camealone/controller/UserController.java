package org.team2project.camealone.controller;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.team2project.camealone.member.IMemberMapper;
import org.team2project.camealone.member.MemberDTO;
import org.team2project.camealone.service.LoginService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private HttpSession session;

    @Autowired
    private LoginService loginService;

    @Autowired
    private IMemberMapper mapper;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/loginService")
    @ResponseBody
    public Map<String, Object> loginService(@RequestBody Map<String, String> loginData, RedirectAttributes redirectAttributes) {
        Map<String, Object> response = new HashMap<>();

        String id = loginData.get("id");
        String password = loginData.get("password");
        String msg = loginService.loginService(id, password);

        logger.info("loginService()에서 리턴된 msg : " + msg);

        if (msg.equals("성공")) {
            response.put("success", true);

            session.setAttribute("id", id); // 세션에 아이디 저장
            session.setMaxInactiveInterval(60 * 30); // 세션 유지 시간 : 60 * 30 = 1800초(30분)
            redirectAttributes.addFlashAttribute("msg", msg);

            String name = mapper.findName(id);
            logger.info("name : " + name);
            logger.info("id : " + id);
            if (name != null) {
                session.setAttribute("name", name);
            }

            SecurityContextHolder.getContext().setAuthentication(SecurityContextHolder.getContext().getAuthentication());
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

    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteUser(@RequestBody Map<String, String> data) { // Authentication authentication
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

    @PostMapping("/update")
    public ResponseEntity<Map<String, String>> updateUserInfo(@RequestBody Map<String, String> data) {
        // 현재 로그인한 회원의 세션 ID를 가져옵니다.
        String sessionId = (String) session.getAttribute("id");
        // 응답 메시지를 담을 맵을 생성합니다.
        Map<String, String> response = new HashMap<>();

        if (sessionId == null) {
            // 세션이 만료된 경우
            response.put("message", "세션이 만료되었습니다, 다시 로그인하세요");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response); // 401 Unauthorized
        }

        // 세션 ID를 이용하여 회원 정보를 조회합니다.
        MemberDTO memberDTO = mapper.findById(sessionId);

        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

        if (memberDTO == null || !encoder.matches(data.get("currentPassword"), memberDTO.getPassword())) {
            // 현재 비밀번호가 일치하지 않는 경우
            response.put("message", "비밀번호를 다시 확인하세요");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // 400 Bad Request
        }

        if (!data.get("newPassword").equals(data.get("confirmNewPassword"))) {
            // 새 비밀번호와 확인 비밀번호가 일치하지 않는 경우
            response.put("message", "새 비밀번호가 일치하지 않습니다");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // 400 Bad Request
        }

        // 비밀번호 변경 로직을 수행하고 결과를 저장합니다.
        int result = loginService.updateUserInfo(sessionId, data.get("newPassword"));
        if (result != 0) {
            // 비밀번호 변경에 성공하면 세션을 무효화하고 로그아웃 처리합니다.
            session.invalidate();
            response.put("message", "비밀번호가 성공적으로 변경되었습니다. 다시 로그인하세요.");
            return ResponseEntity.ok(response); // 200 OK
        } else {
            // 비밀번호 변경에 실패한 경우
            response.put("message", "알 수 없는 오류로 실패했습니다");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // 500 Internal Server Error
        }
    }
}