//package test;
//
//import jakarta.servlet.http.HttpSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import test.KaKaoService;
//
//import java.util.Map;
//
//@Controller
//public class KakaoController {
//
//    @Value("${kakao.client-id}")
//    private String clientId;
//
//    @Value("${kakao.redirect-uri}")
//    private String redirectUri;
//
//    @Autowired
//    private KaKaoService kakaoService;
//
//    @GetMapping("/kakao/login")
//    public String login() {
//        String kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize?response_type=code";
//        kakaoAuthUrl += "&client_id=" + clientId;
//        kakaoAuthUrl += "&redirect_uri=" + redirectUri;
//        return "redirect:" + kakaoAuthUrl;
//    }
//
//    @GetMapping("/kakaologin")
//    public String callback(@RequestParam String code, Model model, HttpSession session) {
//        String accessToken = kakaoService.getAccessToken(code);
//        if (accessToken != null) {
//            Map<String, Object> userInfo = kakaoService.getUserInfo(accessToken);
//            session.setAttribute("user", userInfo);
//            model.addAttribute("user", userInfo);
//            return "redirect:/";
//        } else {
//            return "redirect:/error";
//        }
//    }
//
//}
