package pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import pack.webCrawling.SeleniumCrawler;

import java.util.List;

@Controller
public class CrawlingController {

    @Autowired
    private SeleniumCrawler crawler;

    @GetMapping("/shows")
    public String getShows(Model model) {
        List<String> shows = crawler.getShows();
        model.addAttribute("shows", shows);
        return "shows"; // shows.html 템플릿으로 데이터 전달
    }
}
