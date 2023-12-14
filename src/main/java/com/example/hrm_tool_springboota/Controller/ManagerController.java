package com.example.hrm_tool_springboota.Controller;

import com.example.hrm_tool_springboota.Modal.LeaveApplication;
import com.example.hrm_tool_springboota.Modal.TrainingRecord;
import com.example.hrm_tool_springboota.Repository.LeaveRepository;
import com.example.hrm_tool_springboota.Repository.TrainingRecordRepository;
import com.example.hrm_tool_springboota.Service.EmailService;
import com.example.hrm_tool_springboota.Service.LeaveService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/api/v3")
public class ManagerController {
    private LeaveRepository leaveRepository;
    private TrainingRecordRepository trainingRecordRepository;
    private LeaveService leaveService;

    private EmailService emailService;
@Autowired
    public ManagerController(LeaveRepository leaveRepository, TrainingRecordRepository trainingRecordRepository, LeaveService leaveService, EmailService emailService) {
        this.leaveRepository = leaveRepository;
    this.trainingRecordRepository = trainingRecordRepository;
    this.leaveService = leaveService;

    this.emailService = emailService;
}

    @GetMapping("/Manager__Dashboard")
    public String getDashboard() {
    return "Mdashboard";
}
@GetMapping("/Applications")
    public String getApplications(Model model) {
    List<LeaveApplication> leaveApplications=leaveRepository.findAllByQuery();

    model.addAttribute("Applications", leaveApplications);
    return "M-Application";
}
@GetMapping("/Approval/{id}")
    public String getApproval(@PathVariable("id") Long id,LeaveApplication leaveApplication) throws MessagingException {
   emailService.getApprovalMail(leaveApplication);
    leaveService.getMApprovalStatus(id,leaveApplication);

    return "redirect:/api/v3/Applications";
}
@GetMapping("/Disapproval/{id}")
    public String getDisapproval(@PathVariable("id") Long id,LeaveApplication leaveApplication) throws MessagingException {
     leaveService.getMDisapprovalStatus(id,leaveApplication);

    return "redirect:/api/v3/Applications";
}
@GetMapping(path="/Training")
    public String getTraining(Model model){
    List<TrainingRecord> record=trainingRecordRepository.findAll();
    model.addAttribute("training",record);
    List <Integer> trainingtypeExternal=trainingRecordRepository.getExternalTrainingCount();
    model.addAttribute("trainingexternal",trainingtypeExternal);
    List <Integer> traininginteranl=trainingRecordRepository.getInternalTrainingCount();
    model.addAttribute("intergenerational",traininginteranl);

    return "training";
}


}
