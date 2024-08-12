package pack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Controller
public class NaverCallbackController {

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           Model model) {
        try {
            String clientId = "DHN5r0yyGsG_yoEadr8S";
            String clientSecret = "sHAc_0Ruzq";
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
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();

                // JSON 응답을 모델에 추가
                model.addAttribute("tokenResponse", response.toString());

                return "callback"; // callback.html을 반환
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();
                model.addAttribute("errorMessage", "Failed to get access token: " + response.toString());
                return "error"; // error.html을 반환
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Exception occurred: " + e.getMessage());
            return "error"; // error.html을 반환
        }
    }
}


