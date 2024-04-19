package com.enviro.assessment.grad001.lebohangkhaeane.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author Lebohang
 */
@Entity
@Data
@NoArgsConstructor
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid")
    private int id;

    private String fileName;
    private String fileType;

    @Lob
    private byte[] data;

    /**
     *
     * @param fileName
     * @param fileType
     * @param data
     */
    public Files(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }
}
