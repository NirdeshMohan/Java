package com.ishnit.repository;

import com.ishnit.entity.Department;
import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DepartmentRepositoryTest {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        Department department = Department.builder()
                .deptName("TEST")
                .deptCode("TEST-001")
                .deptAddress("TEST-PIT-PA")
                .build();
        testEntityManager.persist(department);
    }

    @IgnoreForBinding
    public void whenFindById_thenReturnDepartment(){
        Department department = departmentRepository.findById(3L).get();
        assertEquals(department.getDeptName(), "TEST");
    }
}