package com.example.newssearch.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.UUID;


@Entity
@Table(name = "news")
@AllArgsConstructor
@NoArgsConstructor
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
public class NewsArticle {
    @Id
    UUID artId;
    @Embedded
    Source source;
    String author;
    @Column(length = 1024)
    String title;
    @Column(length = 1024)
    String description;
    String url;
    String urlToImage;
    Instant publishedAt;
    String content;
}
