package com.website.mywebsite.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;

    @OneToMany(mappedBy = "author")
    private List<Article> createdArticles;

    @OneToMany(mappedBy = "user")
    private List<FileInfo> createdFiles;

    @Enumerated(value = EnumType.STRING)
    private State state;

    private String confirmCode;

    private Role role;

}
