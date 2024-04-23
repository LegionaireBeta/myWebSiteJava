package com.website.mywebsite.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;
    private String name;
    private String type;

    @Column(length = 1000)
    private String text;

    @ManyToOne
    @JoinTable(name = "article_author")
    private User author;

    @ManyToMany
    @JoinTable(name = "article_likes",
            joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "articleId"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"))
    private List<User> likes;

}
