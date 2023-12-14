package com.example.hrm_tool_springboota.Modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity(name = "mails")
public class Email {
    @Id
    private String id;
    private String from;
    private String to;
    private String subject;
    private String body;
    @OneToMany
    private List<LeaveApplication> leaveApplication;
}
