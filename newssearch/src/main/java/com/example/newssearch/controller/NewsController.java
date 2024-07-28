package com.example.newssearch.controller;

import com.example.newssearch.service.NewsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public ResponseEntity<Object> getNews(
        @RequestParam(name = "q") String keyword,
        @RequestParam(required = false, defaultValue = "12", name = "i") int interval,
        @RequestParam(required = false, defaultValue = "hours", name = "u") String unit
    ) {

        return ResponseEntity.ok(newsService.getNews(keyword, interval, unit));
    }
}
