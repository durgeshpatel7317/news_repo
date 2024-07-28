package com.example.newssearch.service;

import com.example.newssearch.config.AppPropsConfig;
import com.example.newssearch.model.Article;
import com.example.newssearch.model.ExtNewsDto;
import com.example.newssearch.model.NewsArticle;
import com.example.newssearch.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Slf4j
@Service
public class ExtNewsApiService {
    private final RestClient restClient;
    private final URI newsUri;

    public ExtNewsApiService(AppPropsConfig appPropsConfig, RestClient restClient) {
        this.restClient = restClient;
        this.newsUri = UriComponentsBuilder.fromUriString(appPropsConfig.getUrl())
            .queryParam("apiKey", appPropsConfig.getApiKey())
            .build().toUri();
    }

    public List<NewsArticle> getNewsArticles(String keyword) {
        try {
            ExtNewsDto extNewsDto = getNewsArticleFromApi(URI.create(newsUri + "&q=" + keyword));
            return convertToNewsArticle(extNewsDto);
        } catch (Exception e) {
            log.error("Error occurred while getting news from ext system - ", e);
            return Collections.emptyList();
        }
    }

    public ExtNewsDto getNewsArticleFromApi(URI uri) {
        return restClient.get()
            .uri(uri)
            .retrieve()
            .body(ExtNewsDto.class);
    }

    private List<NewsArticle> convertToNewsArticle(ExtNewsDto extNewsDto) {
        List<NewsArticle> newsArticles = new ArrayList<>();
        List<Article> articles = Objects.requireNonNullElse(extNewsDto.getArticles(), Collections.emptyList());
        for (Article article : articles) {
            NewsArticle newsArticle = CommonUtils.convertArticleToNewsArticle(article);
            newsArticle.setArtId(UUID.randomUUID());
            newsArticles.add(newsArticle);
        }
        return newsArticles;
    }
}
