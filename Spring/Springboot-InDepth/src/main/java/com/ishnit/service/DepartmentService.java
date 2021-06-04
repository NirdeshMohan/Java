package com.ishnit.service;

import com.ishnit.entity.Department;

import java.util.List;


public interface DepartmentService {
    public Department createDepartment(Department department);

    public List<Department> getDepartmentList();

    public Department getDepartmentById(Long deptId);

    public void deleteDepartmentById(Long deptId);

    public Department updateDepartmentById(Long deptId, Department dept);

    public Department getDepartmentByName(String deptName);
}
