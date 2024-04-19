package com.enviro.assessment.grad001.lebohangkhaeane.service;

import com.enviro.assessment.grad001.lebohangkhaeane.entity.Files;
import org.springframework.web.multipart.MultipartFile;

public interface FilesService {

    /**
     *
     * @param file
     * @return
     * @throws Exception
     */
    Files saveFiles(MultipartFile file) throws Exception;

    /**
     *
     * @param fileId
     * @return
     * @throws Exception
     */
    Files getFiles(String fileId) throws Exception;
}
