package pack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

@Controller
public class NaverLoginController {

    @GetMapping("/naverLogin")
    public String naverLogin() throws UnsupportedEncodingException {
        String clientId = "DHN5r0yyGsG_yoEadr8S"; // 네이버 개발자 센터에서 발급받은 클라이언트 ID
        String redirectURI = URLEncoder.encode("http://localhost:8080/callback", "UTF-8");
        String state = UUID.randomUUID().toString(); // CSRF 공격 방지를 위한 상태 값 생성

        // 네이버 로그인 URL 생성
        String authorizationURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
                + "&client_id=" + clientId
                + "&redirect_uri=" + redirectURI
                + "&state=" + state;

        // 클라이언트를 네이버 로그인 페이지로 리다이렉트
        return "redirect:" + authorizationURL;
    }
}
