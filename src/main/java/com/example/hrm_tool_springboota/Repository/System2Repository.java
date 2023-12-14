package com.example.hrm_tool_springboota.Repository;

import com.example.hrm_tool_springboota.Modal.FileSystem2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface System2Repository extends JpaRepository<FileSystem2,Long> {
}
