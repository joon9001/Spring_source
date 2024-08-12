package pack.webCrawling;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeleniumCrawler {

    public List<String> getShows() {
        List<String> showsList = new ArrayList<>();
        System.setProperty("webdriver.chrome.driver", "C:\\work2\\sprsou\\sprwebFinal\\chromedriver"); // ChromeDriver 경로 설정

        WebDriver driver = new ChromeDriver();
        
        try {
            driver.get("https://korean.visitseoul.net/exhibition#tabAll&selectedMonth=202408");

            // 필요한 데이터를 가져오기 위해 적절한 요소를 찾기
            List<WebElement> exhibitions = driver.findElements(By.cssSelector("css-selector-for-exhibitions")); // 실제 CSS 선택자 사용

            for (WebElement exhibition : exhibitions) {
                String title = exhibition.findElement(By.cssSelector("css-selector-for-title")).getText(); // 실제 CSS 선택자 사용
                String date = exhibition.findElement(By.cssSelector("css-selector-for-date")).getText(); // 실제 CSS 선택자 사용
                String place = exhibition.findElement(By.cssSelector("css-selector-for-place")).getText(); // 실제 CSS 선택자 사용
                String showInfo = "전시 제목: " + title + "<br/>전시 날짜: " + date + "<br/>전시 장소: " + place;
                showsList.add(showInfo);
            }
        } finally {
            driver.quit();
        }

        return showsList;
    }
}




