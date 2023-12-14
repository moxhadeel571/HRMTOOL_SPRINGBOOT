package com.example.hrm_tool_springboota.Modal;

import jakarta.persistence.*;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Entity(name="filesystem")
public class FileSystem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Remove any manual assignment of this field

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] filedata;

    // Remove any association with FilesUP entity

    private String filename;
    private String fileContent;
}

