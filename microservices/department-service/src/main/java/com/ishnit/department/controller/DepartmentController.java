package com.ishnit.department.controller;

import com.ishnit.department.entity.Department;
import com.ishnit.department.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/departments")
    public Department saveDepartment(@RequestBody Department department){
        log.info("Inside save method of DepartmentController.");
        return departmentService.saveDepartment(department);

    }

    @GetMapping("/departments/{id}")
    public Optional<Department> findDepartment(@PathVariable("id") Long departmentId){
        log.info("Inside find method of DepartmentController.");
        return departmentService.findDepartment(departmentId);

    }
}
