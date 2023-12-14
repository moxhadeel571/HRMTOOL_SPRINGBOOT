package com.example.hrm_tool_springboota.Service;

import com.example.hrm_tool_springboota.Modal.Emplyees;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {
    Long totalEmployees();

    Long activeworkers();

    Long fullTim();

    Long partTime();

    Long gendercodeFemal();

    Long gendercodeMale();

    void deleteEmployee(Long id);

    Page<Emplyees> Page(int page, int size);
}
