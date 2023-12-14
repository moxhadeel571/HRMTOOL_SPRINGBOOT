package com.example.hrm_tool_springboota.Repository;

import com.example.hrm_tool_springboota.Modal.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, String> {
}
