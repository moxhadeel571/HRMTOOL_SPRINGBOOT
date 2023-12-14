package com.example.hrm_tool_springboota.Modal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "leave_applications")
public class LeaveApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private Long file_id ;
    private String full_name;
    private String additional_comments;
    private String department;
    @Column(name = "employee_id")
    private String employee_id;
    private String job_title;
    @OneToMany // or CascadeType.ALL
    private List<FileSystem2> file;
    @ManyToOne
    private Email emails;
    private String leave_type;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end_date;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start_date;
    private Integer total_days;
    private String leave_reason;
    private String email;
    private String phone;
    private String alternative_contact;
    private String supervisor_approval;
    private String Manager_approval;



    // Getters and setters
}
