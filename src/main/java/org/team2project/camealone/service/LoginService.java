package org.team2project.camealone.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.team2project.camealone.member.IMemberMapper;
import org.team2project.camealone.member.MemberDTO;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginService {
    @Autowired private IMemberMapper mapper;
    @Autowired private HttpSession session;

    @Transactional
    public String register(MemberDTO memberDTO) {
        if (memberDTO.getId() == null || memberDTO.getId().trim().isEmpty()) {
            return "아이디는 필수항목입니다";
        }
        if (memberDTO.getName() == null || memberDTO.getName().trim().isEmpty()) {
            return "이름은 필수항목입니다";
        }
        if (memberDTO.getPassword() == null || memberDTO.getPassword().trim().isEmpty()) {
            return "비밀번호는 필수항목입니다";
        }
        if (memberDTO.getEmail() == null || memberDTO.getEmail().trim().isEmpty() || !memberDTO.getEmail().contains("@")) {
            return "유효하지 않은 이메일입니다";
        }

        MemberDTO check = mapper.login(memberDTO.getId());
        if (check != null) {
            return "이미 사용중인 아이디입니다";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(memberDTO.getPassword());
        memberDTO.setPassword(encode);

        int result = mapper.addMember(memberDTO);
        if (result == 1) {
            return "success";
        } else {
            return "알 수 없는 이유로 가입에 실패했습니다";
        }
    }

    public String loginService(String id, String password) {
        if (id == null || id.trim().isEmpty()) {
            return "아이디를 입력하세요";
        }
        if (password == null || password.trim().isEmpty()) {
            return "비밀번호를 입력하세요";
        }

        MemberDTO check = mapper.login(id);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (check != null && encoder.matches(password, check.getPassword())) {
            session.setAttribute("id", check.getId());
            session.setAttribute("email", check.getEmail());
            session.setAttribute("password", check.getPassword());
            return "success";
        } else if (check == null) {
            return "회원가입을 먼저 해주세요.";
        } else {
            return "Invalid username or password";
        }
    }
}
