package com.ishnit.service;

import com.ishnit.entity.Department;
import com.ishnit.error.DepartmentNotFoundException;
import com.ishnit.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @MockBean
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department dept =
                Department.builder()
                          .deptName("IT")
                          .deptAddress("Pittsburgh, PA")
                          .deptCode("IT-001")
                          .deptId(1L)
                          .build();
        Mockito.when(departmentRepository.findByDeptNameIgnoreCase("IT"))
                .thenReturn(dept);
    }


    @Test
    @DisplayName("Get Department Data based on Valid Department Name")
    public void whenValidDepartmentName_thenDepartShouldFound(){
        String deptName = "IT";
        Department found = departmentService.getDepartmentByName(deptName);
        assertEquals(deptName, found.getDeptName());
    }
}

//    public Department createDepartment(Department department);
//
//    public List<Department> getDepartmentList();
//
//    public Department getDepartmentById(Long deptId) throws DepartmentNotFoundException;
//
//    public void deleteDepartmentById(Long deptId);
//
//    public Department updateDepartmentById(Long deptId, Department dept);