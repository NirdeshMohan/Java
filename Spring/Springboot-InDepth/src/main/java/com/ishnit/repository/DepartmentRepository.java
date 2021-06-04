package com.ishnit.repository;

import com.ishnit.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Department findByDeptName(String deptName);

    public Department findByDeptNameIgnoreCase(String deptName);
}
