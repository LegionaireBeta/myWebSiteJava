package com.website.mywebsite.repositories;

import com.website.mywebsite.models.FileInfo;
import com.website.mywebsite.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<FileInfo, Long> {
    FileInfo findByStorageFileName (String fileName);
    boolean existsByFileIdAndLikesContaining(Long fileId, User user);
}
