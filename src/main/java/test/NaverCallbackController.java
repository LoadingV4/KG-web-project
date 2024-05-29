package test;

import com.github.scribejava.apis.NaverApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Controller
@RequestMapping("/login/naver/callback")
public class NaverCallbackController {
    private final Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private static final String CLIENT_ID = "SbrF12qYbsjo2FiRpmsn";
    private static final String CLIENT_SECRET = "Gbv2egcq7K";
    private static final String REDIRECT_URI = "http://localhost:8080/login/naver/callback";
    private static final String MAIN_PAGE_URI = "http://localhost:8080/";

    @GetMapping("/login/naver/callback")
    public String handleCallback(@RequestParam("code") String code, RedirectAttributes redirectAttributes) throws IOException {
        OAuth20Service service = new ServiceBuilder(CLIENT_ID)
                .apiSecret(CLIENT_SECRET)
                .callback(REDIRECT_URI)
                .build(NaverApi.instance());

        OAuth2AccessToken accessToken;
        try {
            accessToken = service.getAccessToken(code);
        } catch (InterruptedException | ExecutionException | IOException e) {
            throw new RuntimeException(e);
        }

        // 네이버 API로부터 사용자 정보 요청
        OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, "https://openapi.naver.com/v1/nid/me");
        service.signRequest(accessToken, oauthRequest);
        com.github.scribejava.core.model.Response oauthResponse;
        try {
            oauthResponse = service.execute(oauthRequest);
        } catch (InterruptedException | ExecutionException | IOException e) {
            throw new RuntimeException(e);
        }

        // 응답에서 사용자 정보 추출
        String userInfo = oauthResponse.getBody();

        // JSON 파싱
        JSONObject jsonObject = new JSONObject(userInfo);
        JSONObject naverUser = jsonObject.getJSONObject("response");
        String email = naverUser.getString("email");
        String name = naverUser.getString("name");
        String birth = naverUser.getString("birthday");

        // DB에 저장하는 로직 수행
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.saveUserInfoToDB(email, name, birth);

        // 리다이렉트 할 페이지 또는 뷰로 전달할 속성 설정
        redirectAttributes.addFlashAttribute("email", email);
        redirectAttributes.addFlashAttribute("name", name);
        redirectAttributes.addFlashAttribute("birth", birth);

        return "redirect:" + MAIN_PAGE_URI;
    }
}
