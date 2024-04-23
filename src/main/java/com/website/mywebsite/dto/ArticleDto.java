package com.website.mywebsite.dto;

import com.website.mywebsite.models.Article;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleDto {

    private Long articleId;
    private String name;
    private String type;
    private String text;
    private String author;
    private Integer likes;

    public static ArticleDto from(Article article){
        if (article.getLikes() == null) {
            return ArticleDto.builder()
                    .articleId(article.getArticleId())
                    .name(article.getName())
                    .type(article.getType())
                    .text(article.getText())
                    .author(article.getAuthor().getEmail())
                    .likes(0)
                    .build();
        }
        else {
            return ArticleDto.builder()
                    .articleId(article.getArticleId())
                    .name(article.getName())
                    .type(article.getType())
                    .text(article.getText())
                    .author(article.getAuthor().getEmail())
                    .likes(article.getLikes().size())
                    .build();
        }
    }

    public static List<ArticleDto> articleList(List<Article> articles) {
        return articles.stream()
                .map(ArticleDto::from)
                .collect(Collectors.toList());
    }
}
