package com.example.hrm_tool_springboota.Modal;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity
public class FileSystem2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private byte[] filedata;

    // Remove any association with FilesUP entity

    private String filename;
    private String fileContent;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}

