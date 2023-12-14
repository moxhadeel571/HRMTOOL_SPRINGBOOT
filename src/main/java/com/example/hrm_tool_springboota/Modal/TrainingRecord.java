package com.example.hrm_tool_springboota.Modal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "training_and_development_data")
public class TrainingRecord {

    @Id
    private int Employee_id;
    private Date Training_Date;
    private String Training_Program_Name;
    private String Training_Type;
    private String trainingoutcome;
    private String Location;
    private String Trainer;
    private int Training_Duration_Days;
    private double Training_Cost;
    // Constructors



}
