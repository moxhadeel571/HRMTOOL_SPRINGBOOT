package com.example.hrm_tool_springboota.Repository;

import com.example.hrm_tool_springboota.Modal.FileSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FIleRepository extends JpaRepository<FileSystem,Long> {
    @Query(value = "select filedata from filesystem where id = :id", nativeQuery = true)
    byte[] getFileData(Long id);

    @Query(value = "select file_content from filesystem where id = :id", nativeQuery = true)
    String getContentType(Long id);

    @Query(value = "select filename from filesystem where id = :id", nativeQuery = true)
    String getFilename(Long id);

    @Query(value = "select * from filesystem " ,nativeQuery = true)
    List<FileSystem> findAllByrepo();
}
