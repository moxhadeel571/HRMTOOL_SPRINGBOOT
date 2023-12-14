package com.example.hrm_tool_springboota.Modal;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.math.BigInteger;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Data
@Entity(name = "employee_data")
public class Emplyees {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ï»¿EmpID")
    @Id
    private Long Employee_ID;
    private String FName;
    private String LName;

    private String StartDate;

    private String ExitDate;

    private String Title;

    private String Supervisor;

    private String ADEmail;

    private String businessunit;
    private String empstatus;

    private String emptype;

    private String payzone;

    private String empclassificationype;

    private String TerminationType;

    private String TerminationDescription;

    private String departmenttype;

    private String Division;

    private String DOB;

    private String State;

    private String JobFunctionDescription;

    private String GenderCode;
    private Long lcode;

    private String RaceDesc;

    private String MaritalDesc;

    private String performanceScore;





}
