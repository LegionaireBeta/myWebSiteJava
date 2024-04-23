package com.website.mywebsite.repositories;

import com.website.mywebsite.models.Article;
import com.website.mywebsite.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    boolean existsByArticleIdAndLikesContaining(Long articleId, User user);
    @Query("select article from Article article where (:q = 'empty' or UPPER(article.name) like UPPER(concat('%', :q, '%'))) ")
    Page<Article> search(@Param("q") String q, Pageable pageable);
}
