package com.website.mywebsite.dto;


import com.website.mywebsite.models.Article;
import com.website.mywebsite.models.FileInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileInfoDto {

    private Long fileId;
    private String originalFileName;
    private String storageFileName;
    private Long size;
    private String type;
    private String url;
    private String description;
    private String user;
    private Integer likes;

    public static FileInfoDto from(FileInfo fileInfo) {
        if (fileInfo.getLikes() == null) {
            return FileInfoDto.builder()
                    .fileId(fileInfo.getFileId())
                    .type(fileInfo.getType())
                    .originalFileName(fileInfo.getOriginalFileName())
                    .storageFileName(fileInfo.getStorageFileName())
                    .size(fileInfo.getSize())
                    .description(fileInfo.getDescription())
                    .url(fileInfo.getUrl())
                    .likes(0)
                    .user(fileInfo.getUser().getEmail())
                    .build();
        } else {
            return FileInfoDto.builder()
                    .fileId(fileInfo.getFileId())
                    .type(fileInfo.getType())
                    .originalFileName(fileInfo.getOriginalFileName())
                    .storageFileName(fileInfo.getStorageFileName())
                    .size(fileInfo.getSize())
                    .description(fileInfo.getDescription())
                    .url(fileInfo.getUrl())
                    .likes(fileInfo.getLikes().size())
                    .user(fileInfo.getUser().getEmail())
                    .build();
        }
    }

    public static List<FileInfoDto> fileLists(List<FileInfo> fileInfos) {
        return fileInfos.stream()
                .map(FileInfoDto::from)
                .collect(Collectors.toList());
    }

}
