package com.example.hrm_tool_springboota.Repository;

import com.example.hrm_tool_springboota.Modal.Emplyees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Emplyees, Long> {
    @Query("SELECT e FROM employee_data e WHERE e.Employee_ID = :id")
    Emplyees findEmployeeById(@Param("id") Long id);
    @Query(value="select count(*) from employee_data")
    Long calculateSumOfEmpIds();
    @Query(value = "SELECT * FROM employee_data WHERE empstatus = 'Active'", nativeQuery = true)
    List<Emplyees> findActiveWorkers();
    @Query(value = "SELECT * FROM employee_data WHERE emptype = 'Contract'", nativeQuery = true)
    List<Emplyees> findfullTime();
    @Query(value = "SELECT * FROM employee_data WHERE emptype = 'Part-Time'", nativeQuery = true)
    List<Emplyees> findPartTime();
    @Query(value = "SELECT * FROM employee_data WHERE GenderCode = 'Female'",nativeQuery = true)
    List<Emplyees> gendercodeFemal();
    @Query(value = "SELECT * FROM employee_data WHERE GenderCode = 'Male'",nativeQuery = true)
    List<Emplyees> gendercodeMale();
    @Query(value = "SELECT * FROM employee_data WHERE ADEmail LIKE %:email%", nativeQuery = true)
    List<Emplyees> findByEmail(@Param("email") String email);


    @Query(value = "SELECT e FROM employee_data e " +
            "WHERE e.Title IS NOT NULL " +
            "  AND e.emptype IS NOT NULL " +
            "  AND e.empclassificationype IS NOT NULL")

    List<Emplyees> findAllTypes(String filters);
}
