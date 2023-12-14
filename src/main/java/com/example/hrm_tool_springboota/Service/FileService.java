package com.example.hrm_tool_springboota.Service;

import com.example.hrm_tool_springboota.Modal.FileSystem;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<FileSystem> listFiles();




    void uploadFile(MultipartFile[] fileData) throws IOException;

    byte[] getFileData(Long id);

    String getContentType(Long id);

    String getFilename(Long id);
}
