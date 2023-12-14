package com.example.hrm_tool_springboota.Repository;

import com.example.hrm_tool_springboota.Modal.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
}
