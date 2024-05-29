package test;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


@Service
public class KaKaoService {
    String accessToken=null;

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @Value("${kakao.client-secret}")
    private String clientSecret;

    public String getAccessToken(String code) {
        String kakaoTokenUrl = "https://kauth.kakao.com/oauth/token";
        StringBuilder postParams = new StringBuilder();
        postParams.append("grant_type=authorization_code");
        postParams.append("&client_id=").append(clientId);
        postParams.append("&redirect_uri=").append(redirectUri);
        postParams.append("&code=").append(code);
        postParams.append("&client_secret=").append(clientSecret); // 선택적, 설정되어 있는 경우 추가

        try {
            URL url = new URL(kakaoTokenUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            bw.write(postParams.toString());
            bw.flush();
            bw.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                ObjectMapper om = new ObjectMapper();
                Map<String, String> map = om.readValue(br, new TypeReference<Map<String, String>>() {});
                accessToken=map.get("access_token");
                return accessToken;
            } else {
                System.out.println("Error response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accessToken;
    }



    public Map<String, Object> getUserInfo(String accessToken) {
        String reqUrl = "https://kapi.kakao.com/v2/user/me";
        Map<String, Object> userInfo = new HashMap<>();

        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                ObjectMapper om = new ObjectMapper();
                userInfo = om.readValue(br, new TypeReference<Map<String, Object>>() {});
            } else {
                System.out.println("Error response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfo;
    }
}
