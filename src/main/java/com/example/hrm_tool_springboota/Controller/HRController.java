package com.example.hrm_tool_springboota.Controller;

import com.example.hrm_tool_springboota.Modal.Email;
import com.example.hrm_tool_springboota.Modal.Emplyees;
import com.example.hrm_tool_springboota.Modal.LeaveApplication;
import com.example.hrm_tool_springboota.Modal.News;
import com.example.hrm_tool_springboota.Repository.EmployeeRepository;
import com.example.hrm_tool_springboota.Repository.LeaveRepository;
import com.example.hrm_tool_springboota.Service.EmailService;
import com.example.hrm_tool_springboota.Service.EmployeeService;
import com.example.hrm_tool_springboota.Service.LeaveService;
import com.example.hrm_tool_springboota.Service.NewsService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api/v1")
public class HRController {
    private EmployeeService employeeService;
    private LeaveService leaveService;
    private LeaveRepository leaveRepository;
    private EmployeeRepository employeeRepository;
    private EmailService emailService;
    private NewsService newsService;
@Autowired
    public HRController(EmployeeService employeeService, LeaveService leaveService, LeaveRepository leaveRepository, EmployeeRepository employeeRepository, EmailService emailService, NewsService newsService) {
        this.employeeService = employeeService;
    this.leaveService = leaveService;
    this.leaveRepository = leaveRepository;
    this.employeeRepository = employeeRepository;
    this.emailService = emailService;
    this.newsService = newsService;
}

    @GetMapping(path = "/dashboard")
    public String dashboard(Model model) {
        Long workers= employeeService.totalEmployees();
        model.addAttribute("workers",workers);
        Long Active=employeeService.activeworkers();
        model.addAttribute("active",Active);
        Long PartTime=employeeService.partTime();
        model.addAttribute("partTime",PartTime);
        Long FullTim=employeeService.fullTim();
        model.addAttribute("fullTime",FullTim);
        Long GenderCode=employeeService.gendercodeFemal();
        model.addAttribute("female",GenderCode);
        Long gendercodeMale=employeeService.gendercodeMale();
        model.addAttribute("male",GenderCode);
        return "dashboard";
    }
    @GetMapping("/leaveApp")
    public String getLeaveApplication(Model model) {
    List<LeaveApplication> leavedata=leaveRepository.findAllByQuery();
    model.addAttribute("leaveApplication",leavedata);
        return "LeaveApplication";
    }
    @PostMapping("/send_email")//recruitor
    public String sendEmail(@ModelAttribute("Data_info") Email email) throws MessagingException {
        // Create the email object

        // Send the automated email, passing the CompanyDetails object
        emailService.sendEmail(email);
        System.setProperty("mail.debug", "true");

        // Redirect to the URL mapping for displaying candidates, without the leading slash
        return "redirect:/api/v1/Employee_Data";
    }
    @GetMapping("/employeeDetails/{id}")
    public String getEmployeeDetails(@PathVariable("id")Long id,Model model,@ModelAttribute("Data_info") Email email){
    Emplyees employee=employeeRepository.findEmployeeById(id);
    model.addAttribute("employee", employee);
    return "ViewPage";
    }
    @GetMapping("/Employee_Data")
    public String getEmployee_Data(Model model) {
        Page<Emplyees> employeePage = employeeRepository.findAll(Pageable.ofSize(10));
        System.out.println(employeePage.getContent()); // Print content to console
        model.addAttribute("Employee_Data", employeePage);
        return "Employee_data";
    }
    @GetMapping("/Pageable")
    private String getpage(@RequestParam("page") int page,Model model, @RequestParam("size") int size){
        Page<Emplyees> products = employeeService.Page(page, size);
        model.addAttribute("product", products);
        return "pageable";
    }
    @GetMapping("/DelEmployeeDetails/{id}")
    public String GetDeleteEmployee(@PathVariable("id")Long id){
    employeeService.deleteEmployee(id);
        return "redirect:/api/v1/Employee_Data";
    }
    @GetMapping(path = "/search")
    public String getSearchInput(Model model, @RequestParam("email") String query) {
        List<Emplyees> employees = employeeRepository.findByEmail(query);
        model.addAttribute("search", employees);
        return "Search"; // Make sure you have a Thymeleaf template named "Search.html"
    }
    @GetMapping(path = "/filter")
    public String getfilterInput(Model model,
                                 @RequestParam("filter") String filters
                                 ) {
        List<Emplyees> divisions = employeeRepository.findAllTypes(filters);
        model.addAttribute("departmentTypes", divisions);
        return "filter"; // Make sure you have a Thymeleaf template named "Search.html"
    }


    @GetMapping(value = "/disapproved/{id}")
    public String getDisapproved(@PathVariable("id") Long id,LeaveApplication leaveApplication) throws MessagingException {
    leaveService.getDisapprovalStatus(id,leaveApplication);

    return "redirect:/api/v1/leaveApp";
}
@GetMapping(value = "/approved/{id}")
    public String getapproved(@PathVariable("id") Long id,LeaveApplication leaveApplication) throws MessagingException {
    leaveService.getApprovalStatus(id,leaveApplication);
    return "redirect:/api/v1/leaveApp";
}
@GetMapping("/NewsAndAnnouncements")
    public String getAnnouncements(Model model,@ModelAttribute("news") News news) {
    List<News> newsdata= newsService.getNews();
    model.addAttribute("news",newsdata);
    return "NewsAndAnn";
}
@PostMapping("/Postnews")
    public String getPostnews(News news) {
            newsService.createNews(news);
    return "redirect:/api/v1/NewsAndAnnouncements";
}




}


