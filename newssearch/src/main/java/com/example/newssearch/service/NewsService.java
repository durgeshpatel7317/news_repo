package com.example.newssearch.service;

import com.example.newssearch.model.NewsArticle;
import com.example.newssearch.repo.NewsRepository;
import com.example.newssearch.utils.CommonUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NewsService {
    private final ExtNewsApiService extNewsApiService;
    private final NewsRepository repo;

    public NewsService(ExtNewsApiService extNewsApiService, NewsRepository repo) {
        this.extNewsApiService = extNewsApiService;
        this.repo = repo;
    }

    public List<Map<String, Object>> getNews(String keyword, int interval, String unit) {
        List<NewsArticle> newsArticles = extNewsApiService.getNewsArticles(keyword);
        if (newsArticles.isEmpty()) {
            newsArticles = repo.getNewsArticle(keyword);
        } else {
            repo.deleteNewsArticle(keyword);
            newsArticles = repo.saveAll(newsArticles);
        }
        return CommonUtils.groupByInterval(newsArticles, interval, unit);
    }
}
