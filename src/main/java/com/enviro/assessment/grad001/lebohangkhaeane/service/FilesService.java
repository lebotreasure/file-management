package com.enviro.assessment.grad001.lebohangkhaeane.service;

import com.enviro.assessment.grad001.lebohangkhaeane.entity.Files;
import org.springframework.web.multipart.MultipartFile;

public interface FilesService {
    Files saveFiles(MultipartFile file) throws Exception;

    Files getFiles(String fileId) throws Exception;
}
