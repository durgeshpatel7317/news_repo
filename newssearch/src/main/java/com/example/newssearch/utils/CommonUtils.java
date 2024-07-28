package com.example.newssearch.utils;

import com.example.newssearch.model.Article;
import com.example.newssearch.model.NewsArticle;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CommonUtils {
    public static NewsArticle convertArticleToNewsArticle(Article article) {
        return NewsArticle.builder()
            .source(article.getSource())
            .author(article.getAuthor())
            .url(article.getUrl())
            .title(article.getTitle())
            .publishedAt(article.getPublishedAt())
            .urlToImage(article.getUrlToImage())
            .content(article.getContent())
            .description(article.getDescription())
            .build();
    }

    public static ChronoUnit getTimeUnit(String unit) {
        return switch (unit) {
                case "hours" -> ChronoUnit.HOURS;
                case "days" -> ChronoUnit.DAYS;
                case "weeks" -> ChronoUnit.WEEKS;
                case "months" -> ChronoUnit.MONTHS;
                case "years" -> ChronoUnit.YEARS;
                default -> ChronoUnit.MINUTES;
        };
    }

    public static List<Map<String, Object>> groupByInterval(List<NewsArticle> newsArticles, int interval, String unit) {
        return newsArticles.stream()
            .collect(Collectors.groupingBy(article -> {
                Instant instant = article.getPublishedAt();
                LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime roundedDown = roundDown(localDateTime, interval, getTimeUnit(unit));
                return roundedDown.toString();
            }, () -> new TreeMap<>((a, b) -> b.compareTo(a)), Collectors.toList()))
            .entrySet()
            .stream()
            .map(data -> Map.of("date", data.getKey(), "articles", data.getValue()))
            .toList();
    }

    public static LocalDateTime roundDown(LocalDateTime dateTime, int interval, TemporalUnit unit) {
        long unitsSinceEpoch = dateTime.toEpochSecond(ZoneId.systemDefault().getRules().getOffset(dateTime)) / unit.getDuration().getSeconds();
        long roundedUnits = (unitsSinceEpoch / interval) * interval;
        return LocalDateTime.ofEpochSecond(roundedUnits * unit.getDuration().getSeconds(), 0, ZoneId.systemDefault().getRules().getOffset(dateTime));
    }
}
