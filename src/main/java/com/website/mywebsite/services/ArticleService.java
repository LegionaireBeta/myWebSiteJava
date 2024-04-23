package com.website.mywebsite.services;

import com.website.mywebsite.dto.ArticleDto;
import com.website.mywebsite.dto.ArticleForm;

import java.util.List;

public interface ArticleService {

    List<ArticleDto> getArticles(ArticleForm form);
    ArticleDto addArticle(Long userId, ArticleForm form);
    List<ArticleDto> getByUser(Long userId);
    void like(Long userId, Long articleId);
    List<ArticleDto> search(Integer page, Integer size, String query, String sort, String direction);


}
