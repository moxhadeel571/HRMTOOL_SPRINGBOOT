package com.example.hrm_tool_springboota.Repository;

import com.example.hrm_tool_springboota.Modal.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveApplication, Long> {

    @Query(value = "SELECT * FROM `leave_applications`", nativeQuery = true)
    List<LeaveApplication> findAllByQuery();

}
