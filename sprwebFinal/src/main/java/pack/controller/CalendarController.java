package pack.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.UUID;
import java.io.*;

@Controller
public class CalendarController {

	 @GetMapping("/")
	    public String showCalendarMain() {
	        return "calendarMain"; // calendarMain.html 파일을 렌더링합니다.
	    }
	
    @PostMapping("/addSchedule")
    @ResponseBody
    public String addSchedule(@RequestParam("title") String title,
                              @RequestParam("description") String description,
                              @RequestParam("location") String location,
                              @RequestParam("start") String start,
                              @RequestParam("end") String end) {
        String token = "YOUR_ACCESS_TOKEN";
        String header = "Bearer " + token;

        try {
            String apiURL = "https://openapi.naver.com/calendar/createSchedule.json";
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", header);

            String calSum = URLEncoder.encode(title, "UTF-8");
            String calDes = URLEncoder.encode(description, "UTF-8");
            String calLoc = URLEncoder.encode(location, "UTF-8");
            String uid = UUID.randomUUID().toString();

            String scheduleIcalString = "BEGIN:VCALENDAR\n" +
                    "VERSION:2.0\n" +
                    "PRODID:Naver Calendar\n" +
                    "CALSCALE:GREGORIAN\n" +
                    "BEGIN:VTIMEZONE\n" +
                    "TZID:Asia/Seoul\n" +
                    "BEGIN:STANDARD\n" +
                    "DTSTART:19700101T000000\n" +
                    "TZNAME:GMT%2B09:00\n" +
                    "TZOFFSETFROM:%2B0900\n" +
                    "TZOFFSETTO:%2B0900\n" +
                    "END:STANDARD\n" +
                    "END:VTIMEZONE\n" +
                    "BEGIN:VEVENT\n" +
                    "SEQUENCE:0\n" +
                    "CLASS:PUBLIC\n" +
                    "TRANSP:OPAQUE\n" +
                    "UID:" + uid + "\n" +
                    "DTSTART;TZID=Asia/Seoul:" + start.replace("-", "").replace(":", "").replace("T", "T") + "\n" +
                    "DTEND;TZID=Asia/Seoul:" + end.replace("-", "").replace(":", "").replace("T", "T") + "\n" +
                    "SUMMARY:" + calSum + " \n" +
                    "DESCRIPTION:" + calDes + " \n" +
                    "LOCATION:" + calLoc + " \n" +
                    "END:VEVENT\n" +
                    "END:VCALENDAR";

            String postParams = "calendarId=defaultCalendarId&scheduleIcalString=" + scheduleIcalString;
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

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

            return response.toString();
        } catch (Exception e) {
            return e.toString();
        }
    }
}
