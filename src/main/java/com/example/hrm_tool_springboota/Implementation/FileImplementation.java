package com.example.hrm_tool_springboota.Implementation;

import com.example.hrm_tool_springboota.Modal.FileSystem;
import com.example.hrm_tool_springboota.Repository.FIleRepository;
import com.example.hrm_tool_springboota.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileImplementation implements FileService {
    @Autowired
    private FIleRepository fIleRepository;


    @Override
    public List<FileSystem> listFiles() {
        List<FileSystem> files = fIleRepository.findAllByrepo();
        if (files != null && !files.isEmpty()) {
            System.out.println("Files found: " + files);
            return files;
        }
        return null;
    }


    @Override
    public void uploadFile(MultipartFile[] fileData) throws IOException {
        if (fileData != null) {
            List<FileSystem> listFiles = new ArrayList<>();
            for (MultipartFile fileUpload : fileData) {
                FileSystem newFile = new FileSystem();
                newFile.setFiledata(fileUpload.getBytes());
                newFile.setFilename(fileUpload.getOriginalFilename());
                newFile.setFileContent(fileUpload.getContentType());
                listFiles.add(newFile);
            }
            fIleRepository.saveAll(listFiles);
        }
    }

    @Override
    public byte[] getFileData(Long id) {
try {
    byte[] foundData=fIleRepository.getFileData(id);
    if (foundData!=null){
        return foundData;
    }else{
        throw new RuntimeException("file not found");
    }
} catch (Exception e) {
    throw new RuntimeException("Error retrieving file data", e);

}


    }

    @Override
    public String getContentType(Long id) {
        try {
            String contentType = fIleRepository.getContentType(id);
            if (contentType != null) {
                return contentType;
            } else {
                throw new RuntimeException("Content type not found for file with id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving content type", e);
        }
    }


    @Override
    public String getFilename(Long id) {
        try {
            String filename = fIleRepository.getFilename(id);
            if (filename != null) {
                return filename;
            } else {
                throw new RuntimeException("Filename not found for file with id: " + id);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving filename", e);
        }
    }


}


