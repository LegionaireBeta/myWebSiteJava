package com.website.mywebsite.services;

import com.website.mywebsite.dto.FileInfoDto;
import com.website.mywebsite.dto.FileInfoForm;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface FileStorageService {

    String saveFile(MultipartFile file, FileInfoForm form, Long userId);
    void writeFileToResponse(String fileName, HttpServletResponse response);
    List<FileInfoDto> getFiles();
    FileInfoDto like(Long userId, Long fileId);
}
