package pack.token;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class NaverTokenRequest {
    public static void main(String[] args) {
        try {
            String clientId = "DHN5r0yyGsG_yoEadr8S";
            String clientSecret = "sHAc_0Ruzq";
            String code = "AUTHORIZATION_CODE"; // 리다이렉트된 URL에서 받은 인증 코드
            String state = "STATE"; // 초기 요청 시 사용한 state 값
            String redirectURI = URLEncoder.encode("http://localhost:8080/callback", "UTF-8");

            String apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code"
                            + "&client_id=" + clientId
                            + "&client_secret=" + clientSecret
                            + "&redirect_uri=" + redirectURI
                            + "&code=" + code
                            + "&state=" + state;

            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString()); // 접근 토큰이 포함된 응답 출력

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
