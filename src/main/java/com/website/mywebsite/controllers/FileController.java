package com.website.mywebsite.controllers;

import com.website.mywebsite.dto.FileInfoDto;
import com.website.mywebsite.dto.FileInfoForm;
import com.website.mywebsite.models.FileInfo;
import com.website.mywebsite.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/files/{user-id}/upload")
    public String getFilesUploadPage(@PathVariable("user-id") Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "file_upload_page";
    }

    @PostMapping("/files/{user-id}")
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile file,
                                             @PathVariable("user-id") Long userId,
                                             FileInfoForm form) {
        String filePath = fileStorageService.saveFile(file, form, userId);
        return ResponseEntity.ok()
                .body(filePath);
    }

    @GetMapping("/files/{file-name:.+}")
    public void getFile(@PathVariable("file-name") String fileName, HttpServletResponse response){
        fileStorageService.writeFileToResponse(fileName, response);
    }

    @GetMapping("/files/{user-id}/s")
    public String getFiles(@PathVariable("user-id") Long userId, Model model){
        model.addAttribute("userId", userId);
        model.addAttribute("files", fileStorageService.getFiles());
        return "files_page";
    }

    @PostMapping("/files/{user-id}/liked/{file-id}/s")
    @ResponseBody
    public ResponseEntity<FileInfoDto> doLike(@PathVariable("user-id") Long userId,
                                              @PathVariable("file-id") Long fileId) {
        return ResponseEntity.ok(fileStorageService.like(userId, fileId));
    }

    @GetMapping("/files/likes")
    @ResponseBody
    public ResponseEntity<List<FileInfoDto>> getLikes() {
        return ResponseEntity.ok(fileStorageService.getFiles());
    }

}
