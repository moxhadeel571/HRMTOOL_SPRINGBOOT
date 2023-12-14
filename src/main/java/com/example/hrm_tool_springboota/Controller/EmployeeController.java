package com.example.hrm_tool_springboota.Controller;

import com.example.hrm_tool_springboota.Modal.*;
import com.example.hrm_tool_springboota.Repository.AttendenceRepository;
import com.example.hrm_tool_springboota.Repository.LeaveRepository;
import com.example.hrm_tool_springboota.Service.*;
import jakarta.mail.MessagingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v2")
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    private FileService fileService;
    private final LeaveService leaveService;
    private LeaveRepository leaveRepository;
    private AttendenceRepository attendRepository;
    private AttendenceService attendService;
    private NewsService newsService;
    private EmailService emailService;
@Autowired
    public EmployeeController(FileService fileService, LeaveService leaveService, LeaveRepository leaveRepository, AttendenceRepository attendRepository, AttendenceService attendService, NewsService newsService, EmailService emailService) {
    this.fileService = fileService;
    this.leaveService = leaveService;
    this.leaveRepository = leaveRepository;
    this.attendRepository = attendRepository;
    this.attendService = attendService;
    this.newsService = newsService;
    this.emailService = emailService;
}
@GetMapping (path="/announcements")
public String getAnnouncements(Model model) {
List<News> newsList=newsService.getNews();
model.addAttribute("announcements",newsList);
    return "Announcement";
}
    @GetMapping(path = "/user")
    public String user(Model model,  Long id, LeaveApplication leaveApplication,@ModelAttribute("email") Email email) throws MessagingException {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        String formattedDate = sdf.format(new Date());

        emailService.leavemail(leaveApplication,email);
        model.addAttribute("id",id);
        model.addAttribute("currentTime", formattedDate);
        return "User";
    }
    @GetMapping(path="/files")
    public String getfilemanagement(Model model){
    List<FileSystem> files=fileService.listFiles();
    model.addAttribute("file", files);
    return "FileManagement";
    }
    @GetMapping(path="/Downloadfile/{id}")
    public ResponseEntity<ByteArrayResource> getDownloadFile(@PathVariable("id")Long id){
        byte[] fileData = fileService.getFileData(id);
        String contentType = fileService.getContentType(id);
        String filename=fileService.getFilename(id);
        if (fileData!=null){
            ByteArrayResource content =new ByteArrayResource(fileData);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename="+filename)
                    .contentType(MediaType.valueOf(contentType))
                    .contentLength(fileData.length)
                    .body(content);

        } else {
            return ResponseEntity.notFound().build();
        }
        }


    @GetMapping("/ImagesDisplay/{id}")
    public ResponseEntity<byte[]> getImagesDisplay(@PathVariable("id") Long id) {
        byte[] fileData = fileService.getFileData(id);
        String contentType = fileService.getContentType(id);

        if (fileData != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.valueOf(contentType));
            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path="/fileUpload")
    public String getfileupload(Model model, @RequestParam("file") MultipartFile[] file) throws IOException {
    fileService.uploadFile(file);
        return "redirect:/api/v2/files";
    }
    @GetMapping(path = "/leave_application")
    public String leaveApplication(@ModelAttribute("leave")LeaveApplication leaveApplication,Model model) {
    List<LeaveApplication> leaveApplication1=leaveRepository.findAll();
    model.addAttribute("leave", leaveApplication1);
        return "leave_data";
    }
    @PostMapping(path = "/save-leave")
    public String saveleave(
                                            @RequestParam("filedata") MultipartFile[] file,
                                            @ModelAttribute  LeaveApplication leaveApplication,
                                            @ModelAttribute("email") Email email) {
        try {
            // Your existing logic
            leaveService.saveLeave(leaveApplication, file);
            emailService.leavemail(leaveApplication, email);

            // Optionally, you can return some data in the response
            String responseMessage = "Leave saved successfully";

            return "redirect:/api/v2/form";
        } catch (MessagingException | IOException e) {
            // Handle exceptions appropriately
            return String.valueOf(new ResponseEntity<>("Error saving leave: " , HttpStatus.INTERNAL_SERVER_ERROR));
        }
    }
    @GetMapping(path = "/form")
    public String GetForm(@ModelAttribute("email") Email email) {

        return "Leave_Form";
    }



    @GetMapping(path = "/attendence")
    public String attendence(Model model,@ModelAttribute("attendence")Attendance attendance){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        String formattedDateTime = sdf.format(new Date());
        List<Attendance> checkdata=attendRepository.findAll();

        model.addAttribute("attend",checkdata);

        // Add the formatted date and time to the model
        model.addAttribute("currentDateTime", formattedDateTime);
        return "Attendence";
    }
    @PostMapping(value ="/saveCheckInOut")
    public String saveCheckin(Attendance attendance){
attendService.saveCheckin(attendance);
        return "redirect:/api/v2/user";
    }
}
