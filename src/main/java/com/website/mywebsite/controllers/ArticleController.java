package com.website.mywebsite.controllers;


import com.website.mywebsite.dto.ArticleDto;
import com.website.mywebsite.dto.ArticleForm;
import com.website.mywebsite.security.details.UserDetailsImpl;
import com.website.mywebsite.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/user/{user-id}/articles")
    public String getArticles(@PathVariable("user-id") Long userId,
                                ArticleForm form, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("articles", articleService.getArticles(form));
        return "articles_page";
    }

    @GetMapping("/user/{user-id}/userArticles")
    public String getUsersArticles(@PathVariable("user-id") Long userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("articles", articleService.getByUser(userId));
        return "articles_user_page";
    }

    @GetMapping("/user/{user-id}/arti")
    @ResponseBody
    public ResponseEntity<List<ArticleDto>> addArticle(@PathVariable("user-id") Long userId) {
        return ResponseEntity.ok(articleService.getByUser(userId));
    }

    @PostMapping("/user/{user-id}/addArticles")
    @ResponseBody
    public ResponseEntity<ArticleDto> addArticle(@PathVariable("user-id") Long userId, @RequestBody ArticleForm form) {
        return ResponseEntity.ok(articleService.addArticle(userId, form));
    }

    @PostMapping("/user/{user-id}/articles/{article-id}/like")
    public String doLike(@PathVariable("user-id") Long userId,
                         @PathVariable("article-id") Long articleId) {
        articleService.like(userId, articleId);
        return "redirect:/user/{user-id}/articles";
    }

    @GetMapping("/searchArticle")
    @ResponseBody
    public ResponseEntity<List<ArticleDto>> search(
            @RequestParam("size") Integer size,
            @RequestParam("page") Integer page,
            @RequestParam(value = "q", required = false) String query,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "direction", required = false) String direction
    ){
        return ResponseEntity.ok(articleService.search(page, size, query, sort, direction));
    }
}
