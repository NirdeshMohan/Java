package com.ishnit.controller;

import com.ishnit.entity.Department;
import com.ishnit.service.DepartmentService;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    DepartmentService service;

    private final Logger log = LoggerFactory.getLogger(DepartmentController.class);

    @PostMapping("/departments")
    public Department createDepartment(@RequestBody Department department){
        log.info("Adding New Department");
        return service.createDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> getDepartmentList(){
        log.info("Get all Department");
        return service.getDepartmentList();
    }

    @GetMapping("/departments/{id}")
    public Department getDepartmentById(@PathVariable("id") Long deptId){
        log.info("Get a Department");
        return service.getDepartmentById(deptId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long deptId){
        log.info("Delete a Department");
        service.deleteDepartmentById(deptId);
        return "Department Deleted Successfully!!";
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartmentById(@PathVariable("id") Long deptId, @RequestBody Department dept){
        log.info("Update Department");
        return service.updateDepartmentById(deptId, dept);
    }

    @GetMapping("/departments/name/{name}")
    public Department getDepartmentByName(@PathVariable("name") String deptName){
        log.info("Get Department by name");
        return service.getDepartmentByName(deptName);
    }
}
