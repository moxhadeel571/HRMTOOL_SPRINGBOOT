package com.example.hrm_tool_springboota.Implementation;

import com.example.hrm_tool_springboota.Modal.Emplyees;
import com.example.hrm_tool_springboota.Repository.EmployeeRepository;
import com.example.hrm_tool_springboota.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeImplementation implements EmployeeService {
    private EmployeeRepository employeeRepository;
@Autowired
    public EmployeeImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Long totalEmployees() {
        return employeeRepository.calculateSumOfEmpIds();
    }

    @Override
    public Long activeworkers() {
        List<Emplyees> activeWorkersList = employeeRepository.findActiveWorkers();
        return (long) activeWorkersList.size();
    }

    @Override
    public Long fullTim() {
    List<Emplyees> findfullTime = employeeRepository.findfullTime();
        return (long) findfullTime.size();
    }

    @Override
    public Long partTime() {
        List<Emplyees> findPartTime = employeeRepository.findPartTime();

        return (long) findPartTime.size();
    }

    @Override
    public Long gendercodeFemal() {
       List<Emplyees> gendercodeFemal = employeeRepository.gendercodeFemal();

        return (long) gendercodeFemal.size();
    }

    @Override
    public Long gendercodeMale() {

    List<Emplyees> GenderCodeMale=employeeRepository.gendercodeMale();

    return null;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Page<Emplyees> Page(int page, int size) {
    Page emplyees =  employeeRepository.findAll(PageRequest.of(page, size));
        return emplyees;
    }


}
