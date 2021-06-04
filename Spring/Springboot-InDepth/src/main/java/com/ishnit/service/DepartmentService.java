package com.ishnit.service;

import com.ishnit.entity.Department;
import com.ishnit.error.DepartmentNotFoundException;

import java.util.List;


public interface DepartmentService {
    public Department createDepartment(Department department);

    public List<Department> getDepartmentList();

    public Department getDepartmentById(Long deptId) throws DepartmentNotFoundException;

    public void deleteDepartmentById(Long deptId);

    public Department updateDepartmentById(Long deptId, Department dept);

    public Department getDepartmentByName(String deptName);
}
