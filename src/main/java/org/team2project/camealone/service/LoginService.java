package org.team2project.camealone.service;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.team2project.camealone.member.IMemberMapper;
import org.team2project.camealone.member.MemberDTO;

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

        if (isIdDuplicated(memberDTO.getId())) {
            return "이미 가입된 아이디입니다";
        }
        if (isEmailDuplicated(memberDTO.getEmail())) {
            return "이미 가입된 이메일입니다";
        }
        if (isPhoneDuplicated(memberDTO.getPhone())) {
            return "이미 가입된 전화번호 입니다";
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

    private boolean isIdDuplicated(String id) {
        MemberDTO member = mapper.login(id);
        return member != null;
    }

    private boolean isEmailDuplicated(String email) {
        MemberDTO member = mapper.findByEmail(email);
        return member != null;
    }

    private boolean isPhoneDuplicated(String phone) {
        // 이메일과 비슷하게 전화번호를 확인하는 쿼리를 작성합니다.
        MemberDTO member = mapper.findByPhone(phone);
        return member != null;
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
            return "아이디나 비밀번호가 잘못됐습니다";
        }
    }

    // 회원 탈퇴를 처리하는 함수
    public boolean deleteUser(String username, String password) {
        MemberDTO user = mapper.login(username);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (user != null && encoder.matches(password, user.getPassword())) {
            mapper.deleteMember(user.getId());
            return true;
        }
        return false;
    }
}
