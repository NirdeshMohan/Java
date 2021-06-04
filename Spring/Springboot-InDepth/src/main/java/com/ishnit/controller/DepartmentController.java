package com.ishnit.controller;

import com.ishnit.entity.Department;
import com.ishnit.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    DepartmentService service;

    @PostMapping("/departments")
    public Department createDepartment(@RequestBody Department department){
        return service.createDepartment(department);
    }

    @GetMapping("/departments")
    public List<Department> getDepartmentList(){
        return service.getDepartmentList();
    }

    @GetMapping("/departments/{id}")
    public Department getDepartmentById(@PathVariable("id") Long deptId){
        return service.getDepartmentById(deptId);
    }

    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long deptId){
        service.deleteDepartmentById(deptId);
        return "Department Deleted Successfully!!";
    }

    @PutMapping("/departments/{id}")
    public Department updateDepartmentById(@PathVariable("id") Long deptId, @RequestBody Department dept){
        return service.updateDepartmentById(deptId, dept);
    }

    @GetMapping("/departments/name/{name}")
    public Department getDepartmentByName(@PathVariable("name") String deptName){
        return service.getDepartmentByName(deptName);
    }
}
