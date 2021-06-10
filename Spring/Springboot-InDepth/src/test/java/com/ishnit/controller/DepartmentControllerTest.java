package com.ishnit.controller;

import com.ishnit.entity.Department;
import com.ishnit.error.DepartmentNotFoundException;
import com.ishnit.service.DepartmentService;
import com.ishnit.service.DepartmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    Department department;

    @BeforeEach
    void setUp() {
        department = Department.builder()
                .deptAddress("Pittsburgh, PA")
                .deptName("IT")
                .deptCode("IT-007")
                .deptId(1L)
                .build();
    }

    @Test
    void createDepartment() throws Exception {
        Department inputDepartment = Department.builder()
                .deptAddress("Pittsburgh, PA")
                .deptName("IT")
                .deptCode("IT-001")
                .build();
        Mockito.when(departmentService.createDepartment(inputDepartment))
                .thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"deptName\":\"OP\",\n" +
                        "    \"deptAddress\":\"Cleveland, OH\",\n" +
                        "    \"deptCode\":\"OP-002\"\n" +
                        "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void getDepartmentById() throws Exception {
         Mockito.when(departmentService.getDepartmentById(1L))
                .thenReturn(department);

        mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.deptName").value(department.getDeptName()));
    }
}