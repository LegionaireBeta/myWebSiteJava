package com.website.mywebsite.services;


import com.website.mywebsite.dto.FileInfoDto;
import com.website.mywebsite.dto.FileInfoForm;
import com.website.mywebsite.models.FileInfo;
import com.website.mywebsite.models.User;
import com.website.mywebsite.repositories.FilesRepository;
import com.website.mywebsite.repositories.UsersRepository;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Component
public class FileStorageServiceImpl implements FileStorageService{

    @Value("${storage.path}")
    private String storagePath;

    @Autowired
    private FilesRepository filesRepository;

    @Autowired
    private UsersRepository usersRepository;


    @Override
    public String saveFile(MultipartFile file, FileInfoForm form, Long userId) {

        User user = usersRepository.getOne(userId);

        String storageName = UUID.randomUUID().toString() + "." +
                FilenameUtils.getExtension(file.getOriginalFilename());

        FileInfo fileInfo = FileInfo.builder()
                .user(user)
                .originalFileName(file.getOriginalFilename())
                .type(file.getContentType())
                .size(file.getSize())
                .storageFileName(storageName)
                .url(storagePath + "\\" +storageName)
                .description(form.getDescription())
                .build();

        try {
            Files.copy(file.getInputStream(), Paths.get(storagePath, storageName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        filesRepository.save(fileInfo);
        return fileInfo.getStorageFileName();
    }

    @Override
    public void writeFileToResponse(String fileName, HttpServletResponse response) {
        FileInfo fileInfo = filesRepository.findByStorageFileName(fileName);
        response.setContentType(fileInfo.getType());


        try {
            IOUtils.copy(new FileInputStream(fileInfo.getUrl()), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<FileInfoDto> getFiles() {
        List<FileInfo> fileInfos = filesRepository.findAll();
        return FileInfoDto.fileLists(fileInfos);
    }

    @Override
    public FileInfoDto like(Long userId, Long fileId) {
        User user = usersRepository.getOne(userId);
        FileInfo fileInfo = filesRepository.getOne(fileId);

        if (filesRepository.existsByFileIdAndLikesContaining(fileId, user)) {
            fileInfo.getLikes().remove(user);
        } else {
            fileInfo.getLikes().add(user);
        }

        filesRepository.save(fileInfo);
        return FileInfoDto.from(fileInfo);

    }
}
