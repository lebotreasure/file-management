package com.enviro.assessment.grad001.lebohangkhaeane.controller;

import com.enviro.assessment.grad001.lebohangkhaeane.ResponseData;
import com.enviro.assessment.grad001.lebohangkhaeane.entity.Files;
import com.enviro.assessment.grad001.lebohangkhaeane.service.FilesService;
import jakarta.annotation.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1")
public class FilesController {

    private FilesService filesService;

    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        Files files = null;
        String downloadURI = "";
        files = filesService.saveFiles(file);

        downloadURI = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download")
                .path(String.valueOf(files.getId()))
                .toUriString();

        return new ResponseData(files.getFileName(),
                downloadURI,
                file.getContentType(),
                file.getSize());
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileId) throws Exception {
        Files files = null;
        files = filesService.getFiles(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(files.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + files.getFileName()
                                + "\"")
                .body((Resource) new ByteArrayResource(files.getData()));
    }
}
