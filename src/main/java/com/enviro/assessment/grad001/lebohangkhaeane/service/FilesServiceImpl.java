package com.enviro.assessment.grad001.lebohangkhaeane.service;

import com.enviro.assessment.grad001.lebohangkhaeane.entity.Files;
import com.enviro.assessment.grad001.lebohangkhaeane.repository.FilesRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Lebohang
 */
@Service
public class FilesServiceImpl implements FilesService {

    private FilesRepository filesRepository;

    /**
     *
     * @param filesRepository
     */
    public FilesServiceImpl(FilesRepository filesRepository) {
        this.filesRepository = filesRepository;
    }

    /**
     *
     * @param file
     * @return
     * @throws Exception
     */
    @Override
    public Files saveFiles(MultipartFile file) throws Exception {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if(fileName.contains("..")) {
                throw new Exception("Filename contains invalid path sequence"
                + fileName);
            }

            Files files = new Files(fileName,
                    file.getContentType(),
                    file.getBytes());
            return filesRepository.save(files);

        } catch (Exception e) {
            throw new Exception("Could not save file: " + fileName);
        }
    }

    /**
     *
     * @param fileId
     * @return
     * @throws Exception
     */
    @Override
    public Files getFiles(String fileId) throws Exception {
        return filesRepository
                .findById(fileId)
                .orElseThrow(
                        () -> new Exception("File not found with Id: " + fileId));
    }
}
