package com.website.mywebsite.services;

import com.website.mywebsite.dto.ArticleDto;
import com.website.mywebsite.dto.ArticleForm;
import com.website.mywebsite.models.Article;
import com.website.mywebsite.models.User;
import com.website.mywebsite.repositories.ArticleRepository;
import com.website.mywebsite.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<ArticleDto> getArticles(ArticleForm form) {
        List<Article> articles = articleRepository.findAll();
        return ArticleDto.articleList(articles);
    }

    @Override
    public ArticleDto addArticle(Long userId, ArticleForm form) {
        User user = usersRepository.getOne(userId);

        Article article = Article.builder()
                .author(user)
                .name(form.getName())
                .type(form.getType())
                .text(form.getText())
                .build();

        articleRepository.save(article);
        return ArticleDto.from(article);
    }

    @Override
    public List<ArticleDto> getByUser(Long userId) {
        User user = usersRepository.getOne(userId);
        List<Article> articleList = user.getCreatedArticles();
        return ArticleDto.articleList(articleList);
    }

    @Override
    public void like(Long userId, Long articleId){
        User user = usersRepository.getOne(userId);
        Article article = articleRepository.getOne(articleId);
        if (articleRepository.existsByArticleIdAndLikesContaining(articleId, user)) {
            article.getLikes().remove(user);
        }
        else {
            article.getLikes().add(user);
        }
        System.out.println("**********************");
        articleRepository.save(article);
    }

    @Override
    public List<ArticleDto> search(Integer page, Integer size, String query, String sortParameter, String directionParameter) {
        Sort.Direction direction = Sort.Direction.ASC;
        Sort sort = Sort.by(direction, "articleId");

        if (directionParameter != null) {
            direction = Sort.Direction.fromString(directionParameter);
        }

        if (sortParameter != null) {
            sort = Sort.by(direction, sortParameter);
        }

        if (query == null) {
            query = "empty";
        }

        if (size == null) {
            size = 3;
        }

        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Article> papersPage = articleRepository.search(query, pageRequest);
        return ArticleDto.articleList(papersPage.getContent());
    }

}
