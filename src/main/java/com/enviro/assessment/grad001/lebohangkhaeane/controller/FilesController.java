package com.enviro.assessment.grad001.lebohangkhaeane.controller;

import com.enviro.assessment.grad001.lebohangkhaeane.ResponseData;
import com.enviro.assessment.grad001.lebohangkhaeane.entity.Files;
import com.enviro.assessment.grad001.lebohangkhaeane.service.FilesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author Lebohang Khaeane
 */
@RestController
@RequestMapping("/api/v1")
@Tag(name = "File management")
public class FilesController {

    private FilesService filesService;

    /**
     *
     * @param filesService
     */
    public FilesController(FilesService filesService) {
        this.filesService = filesService;
    }

    /**
     * Api documentation customise endpoint
     */
    @Operation(
            description = "Uploading files",
            summary = "This is where the user will upload files for analysis",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorised",
                            responseCode = "403"
                    )
            }
    )
    /**
     *
     * @param file
     * @return Response Data
     * @throws Exception
     */
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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

    /**
     * Api documentation customise endpoint
     */
    @Operation(
            description = "Retrieve files uploaded",
            summary = "The user will retrieve the file using file Id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorised",
                            responseCode = "403"
                    )
            }
    )
    /**
     *
     * @Path file Id
     * @return File Information with ByteArrayResource
     * @throws Exception
     */
    @GetMapping("/download/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileId) throws Exception {
        Files files = null;
        files = filesService.getFiles(fileId);
        return  ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(files.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + files.getFileName()
                                + "\"")
                .body(new ByteArrayResource(files.getData()));
    }
}
