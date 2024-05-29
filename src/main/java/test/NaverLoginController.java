package test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//package org.team2project.camealone.controller;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.client.RestTemplate;
//
//import java.math.BigInteger;
//import java.security.SecureRandom;
//import java.util.HashMap;
//import java.util.Map;
//
@Controller
@RequestMapping("/login")
public class NaverLoginController {
//
//    private final RestTemplate restTemplate = new RestTemplate();
//

//@Value("${naver.client-id}")
//    private String clientId;
//    @Value("${naver.client-secret}")
//    private String clientSecret;
//    @Value("${naver.redirect-uri}")
//    private String redirectUri;
//
//    @GetMapping("/login")
//    public String login() {
//        return "login"; // 로그인 페이지
//    }
//
//    // 로그인 처리
//    public String naverLogin(HttpServletRequest request){
//        String state=generateState();
//        HttpSession session=request.getSession();
//        session.setAttribute("state",state);
//
//        String naverReqURI="https://nid.naver.com/oauth2.0/authorize?client_id="+clientId+"&response_type=code&redirect_uri="+redirectUri+"&state="+state;
//        return "redirect:"+naverReqURI;
//    }
//
//    public String naverCallback(@RequestParam String code, @RequestParam String state, @RequestParam HttpServletRequest request, @RequestParam Model model){
//        // CSRF 방지를 위한 상태 토큰 검증 검증
//        // 세션 또는 별도의 저장 공간에 저장된 상태 토큰과 콜백으로 전달받은 state 파라미터의 값이 일치해야 함
//        HttpSession session=request.getSession();
//        String sessionState=(String) session.getAttribute("state");
//        // 콜백 응답에서 state 파라미터의 값을 가져옴
//
//        if (sessionState != null&&sessionState.equals(state)) {
//            // 액세스 토큰 요청
//            String accessTokenReqURI="https://nid.naver.com/oauth2.0/token?client_id="+clientId
//                    +"&client_secret="+clientSecret
//                    +"&grant_type=authorization_code&state="+state
//                    +"&code="+code;
//
//            // 사용자 정보 요청
//            String userInfoReqURl="https://openapi.naver.com/v1/nid/me";
//
//            Map<String, String> headers=new HashMap<>();
//            headers.put("Authorization","Bearer"+accessTokenReqURI);
//            Map<String, Object> profileResponse=restTemplate.getForObject(userInfoReqURl,Map.class,headers);
//
//            // 사용자 정보를 모델에 추가
//            Map<String, Object> response=(Map<String, Object>) profileResponse.get("response");
//            model.addAttribute("user",response);
//
//            // 로그인 후 Main으로 리디렉션
//            return "redirect:Main";
//        }else {
//            return "error";
//        }
//    }
//
//    // 토큰 생성
//    private String generateState() {
//        SecureRandom random = new SecureRandom();
//        return new BigInteger(130, random).toString(32);
//    }
//}

    private static final String CLIENT_ID = "SbrF12qYbsjo2FiRpmsn";
    private static final String REDIRECT_URI = "http://localhost:8080/login/naver/callback";

    @GetMapping("/naver")
    public String redirectToNaverLoginPage(RedirectAttributes redirectAttributes) {
        String authorizationUrl = "https://nid.naver.com/oauth2.0/authorize?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&response_type=code&state=STATE";
        return "redirect:" + authorizationUrl;
    }


}