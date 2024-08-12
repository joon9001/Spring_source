package pack.token;

import java.net.URLEncoder;
import java.util.UUID;

public class NaverOAuthToken {
    public static void main(String[] args) {
        try {
            // 1. 클라이언트 ID 및 리다이렉트 URI 설정
            String clientId = "DHN5r0yyGsG_yoEadr8S";
            String redirectURI = URLEncoder.encode("http://localhost:8080/callback", "UTF-8");
            String state = UUID.randomUUID().toString(); // 고유한 상태 값 생성

            // 2. 네이버 로그인 URL 생성
            String authorizationURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code"
                                      + "&client_id=" + clientId
                                      + "&redirect_uri=" + redirectURI
                                      + "&state=" + state;

            // 3. 사용자에게 URL을 제공하여 네이버 로그인 페이지로 리다이렉트
            System.out.println("Navigate to the following URL for authentication:");
            System.out.println(authorizationURL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

