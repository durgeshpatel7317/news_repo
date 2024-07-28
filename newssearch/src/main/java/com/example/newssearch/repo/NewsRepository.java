package com.example.newssearch.repo;

import com.example.newssearch.model.NewsArticle;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<NewsArticle, Long> {
    @Modifying
    @Transactional
    @Query("""
        DELETE FROM NewsArticle WHERE title LIKE %:keyword% OR description LIKE %:keyword%
        """)
    void deleteNewsArticle(String keyword);

    @Modifying
    @Transactional
    @Query("""
        SELECT n FROM NewsArticle n WHERE title LIKE %:keyword% OR description LIKE %:keyword%
        """)
    List<NewsArticle> getNewsArticle(String keyword);
}
