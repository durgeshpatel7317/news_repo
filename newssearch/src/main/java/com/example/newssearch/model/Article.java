package com.example.newssearch.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Article {
    Source source;
    String author;
    String title;
    String description;
    String url;
    String urlToImage;
    Instant publishedAt;
    String content;
}
