package com.ishnit.service;

import com.ishnit.entity.Department;
import com.ishnit.error.DepartmentNotFoundException;
import com.ishnit.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentRepository repository;

    @Override
    public Department createDepartment(Department department) {
        return repository.save(department);
    }

    @Override
    public List<Department> getDepartmentList() {
        return repository.findAll();
    }

    @Override
    public Department getDepartmentById(Long deptId) throws DepartmentNotFoundException {
        Optional<Department> department = repository.findById(deptId);

        if(!department.isPresent()){
            throw new DepartmentNotFoundException("Department is not available");
        }
        return department.get();
    }

    @Override
    public void deleteDepartmentById(Long deptId) {
        repository.deleteById(deptId);
    }

    @Override
    public Department updateDepartmentById(Long deptId, Department dept) {
        Department deptFromDB = repository.findById(deptId).get();
        if(Objects.nonNull(deptFromDB.getDeptName()) && ! "".equalsIgnoreCase(deptFromDB.getDeptName())){
            deptFromDB.setDeptName(dept.getDeptName());
        }

        if(Objects.nonNull(deptFromDB.getDeptCode()) && ! "".equalsIgnoreCase(deptFromDB.getDeptCode())){
            deptFromDB.setDeptCode(dept.getDeptCode());
        }

        if(Objects.nonNull(deptFromDB.getDeptAddress()) && ! "".equalsIgnoreCase(deptFromDB.getDeptAddress())){
            deptFromDB.setDeptAddress(dept.getDeptAddress());
        }

        return repository.save(deptFromDB);
    }

    @Override
    public Department getDepartmentByName(String deptName) {
        //return repository.findByDeptName(deptName);
        return repository.findByDeptNameIgnoreCase(deptName);
    }
}
