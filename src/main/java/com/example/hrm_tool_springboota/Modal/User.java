package com.example.hrm_tool_springboota.Modal;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName; // Corrected variable name to follow Java conventions
    private String gender;
    private String confirmPass; // Corrected variable name to follow Java conventions
    private String mobileNo; // Corrected variable name to follow Java conventions
    private String password;
    private String email;
    private String roles;


//    public <R> User(String email, String password, R collect) {
//    }
}
