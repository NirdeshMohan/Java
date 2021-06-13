package com.ishnit.department.service;

import com.ishnit.department.entity.Department;
import com.ishnit.department.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public Department saveDepartment(Department department) {
        log.info("Inside save method of DepartmentService.");
        return departmentRepository.save(department);
    }

    public Optional<Department> findDepartment(Long departmentId) {
        log.info("Inside find method of DepartmentService.");
        return departmentRepository.findById(departmentId);
    }
}
