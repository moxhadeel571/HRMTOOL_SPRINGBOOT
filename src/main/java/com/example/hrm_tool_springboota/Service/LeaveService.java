package com.example.hrm_tool_springboota.Service;

import com.example.hrm_tool_springboota.Modal.LeaveApplication;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface LeaveService {
    void saveLeave(LeaveApplication leaveApplication, MultipartFile[] fileData) throws IOException;




    void getDisapprovalStatus(Long id, LeaveApplication leaveApplication) throws MessagingException;

    void getApprovalStatus(Long id, LeaveApplication leaveApplication) throws MessagingException;

    void getMDisapprovalStatus(Long id, LeaveApplication leaveApplication) throws MessagingException;

    void getMApprovalStatus(Long id, LeaveApplication leaveApplication) throws MessagingException;
}
