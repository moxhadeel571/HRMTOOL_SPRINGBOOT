package com.example.hrm_tool_springboota.Implementation;

import com.example.hrm_tool_springboota.Modal.FileSystem2;
import com.example.hrm_tool_springboota.Modal.LeaveApplication;
import com.example.hrm_tool_springboota.Repository.LeaveRepository;
import com.example.hrm_tool_springboota.Repository.System2Repository;
import com.example.hrm_tool_springboota.Service.EmailService;
import com.example.hrm_tool_springboota.Service.LeaveService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LeaveImplementation implements LeaveService {
    private final LeaveRepository leaveRepository;
    private System2Repository system2Repository;
    private EmailService emailService;

@Autowired
    public LeaveImplementation(LeaveRepository leaveRepository, System2Repository system2Repository, EmailService emailService) {
        this.leaveRepository = leaveRepository;
    this.system2Repository = system2Repository;
    this.emailService = emailService;
}

    @Override
    public void saveLeave(LeaveApplication leaveApplication, MultipartFile[] fileData) throws IOException {
        List<FileSystem2> listFiles = new ArrayList<>();
        for (MultipartFile fileUpload : fileData) {
            FileSystem2 newFile = new FileSystem2();
            newFile.setFiledata(fileUpload.getBytes());
            newFile.setFilename(fileUpload.getOriginalFilename());
            newFile.setFileContent(fileUpload.getContentType());
            system2Repository.save(newFile);
            listFiles.add(newFile);
        }

        LeaveApplication leaveApplication1=new LeaveApplication();
        leaveApplication1.setId(leaveApplication.getId());
        leaveApplication1.setFull_name(leaveApplication.getFull_name());
        leaveApplication1.setAdditional_comments(leaveApplication.getAdditional_comments());
        leaveApplication1.setDepartment(leaveApplication.getDepartment());
        leaveApplication1.setEmployee_id(leaveApplication.getEmployee_id());
        leaveApplication1.setJob_title(leaveApplication.getJob_title());
        leaveApplication1.setLeave_type(leaveApplication.getLeave_type());
        leaveApplication1.setEnd_date(leaveApplication.getEnd_date());
        leaveApplication1.setStart_date(leaveApplication.getStart_date());
        leaveApplication1.setTotal_days(leaveApplication.getTotal_days());
        leaveApplication1.setLeave_reason(leaveApplication.getLeave_reason());
        leaveApplication1.setEmail(leaveApplication1.getEmail());
        leaveApplication1.setPhone(leaveApplication.getPhone());
        leaveApplication1.setAlternative_contact(leaveApplication.getAlternative_contact());
        leaveApplication1.setSupervisor_approval(leaveApplication.getSupervisor_approval());
        // Set the files to the original leaveApplication instance
        leaveApplication.setFile(listFiles);

        // Save the original leaveApplication instance
        leaveRepository.save(leaveApplication);
    }


    @Override
    public void getDisapprovalStatus(Long id,LeaveApplication leaveApplication) throws MessagingException {
        if (id != null) {
            LeaveApplication leavedata = leaveRepository.findById(id).orElse(null);

            if (leavedata != null) {
                leavedata.setSupervisor_approval("Disapprove");  // Setting to 'false' for disapproval
                emailService.getDisapprovalMail(leaveApplication);

                leaveRepository.save(leavedata);
            }
        }
    }


    @Override
    public void getApprovalStatus(Long id,LeaveApplication leaveApplication) throws MessagingException {
        if (id != null) {
            LeaveApplication leavedata = leaveRepository.findById(id).orElse(null);

            if (leavedata != null) {
                leavedata.setSupervisor_approval("Approved");  // Setting to 'false' for disapproval
                emailService.getDisapprovalMail(leaveApplication);
                leaveRepository.save(leavedata);
            }
        }
    }
    @Override
    public void getMDisapprovalStatus(Long id,LeaveApplication leaveApplication) throws MessagingException {
        if (id != null) {
            LeaveApplication leavedata = leaveRepository.findById(id).orElse(null);

            if (leavedata != null) {
                leavedata.setManager_approval("Disapprove");  // Setting to 'false' for disapproval
                emailService.getDisapprovalMail(leaveApplication);

                leaveRepository.save(leavedata);
            }
        }
    }


    @Override
    public void getMApprovalStatus(Long id,LeaveApplication leaveApplication) throws MessagingException {
        if (id != null) {
            LeaveApplication leavedata = leaveRepository.findById(id).orElse(null);

            if (leavedata != null) {
                leavedata.setManager_approval("Approved");  // Setting to 'false' for disapproval
                emailService.getDisapprovalMail(leaveApplication);
                leaveRepository.save(leavedata);
            }
        }
    }


}
