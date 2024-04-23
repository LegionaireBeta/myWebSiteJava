package com.website.mywebsite.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fileId;
    private String originalFileName;
    private String storageFileName;
    private Long size;
    private String type;
    private String url;
    private String description;

    @ManyToOne
    @JoinTable(name = "users_file")
    private User user;

    @ManyToMany
    @JoinTable(name = "file_likes",
            joinColumns = @JoinColumn(name = "file_id", referencedColumnName = "fileId"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "userId"))
    private List<User> likes;


}
