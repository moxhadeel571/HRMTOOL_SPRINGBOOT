package com.example.hrm_tool_springboota.Modal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name="attendence")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Emplyees employee;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "hh:mm:ss a")
    private String checkinTime;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "hh:mm:ss a")
    private String checkoutTime;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String checkoutDate;
}
