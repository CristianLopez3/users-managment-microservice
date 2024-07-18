package com.cristian.tiusers.controller;

import com.cristian.tiusers.dto.DepartmentDto;
import com.cristian.tiusers.exception.DepartmentNotFound;
import com.cristian.tiusers.service.DepartmentService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(DepartmentController.class)
@DisplayName("Department Controller Test")
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Save Department Should Return Created")
    void saveDepartmentShouldReturnCreated() throws Exception {
        DepartmentDto departmentDto = new DepartmentDto(
                "Test Department",
                "Test Description",
                1L);

        mockMvc.perform(post("/api/v1/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departmentDto)))
                .andExpect(status().isCreated())
                .andExpect(content().string("department saved successfully"));
    }


    @Test
    @DisplayName("Update Department Should Return Ok")
    void updateDepartmentShouldReturnOk() throws Exception {
        DepartmentDto departmentDto = new DepartmentDto(
                "Test Department",
                "Test Description",
                1L);

        mockMvc.perform(put("/api/v1/departments/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departmentDto)))
                .andExpect(status().isOk())
                .andExpect(content().string("department updated successfully"));
    }

    @Test
    @DisplayName("Get Department By Id Should Return Ok")
    void getDepartmentByIdShouldReturnOk() throws Exception {
        long departmentId = 1L;

        DepartmentDto departmentDto = new DepartmentDto(
                "Test Department",
                "Test Description",
                1L);

        when(departmentService.getDepartmentById(departmentId)).thenReturn(departmentDto);

        mockMvc.perform(get("/api/v1/departments/" + departmentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(departmentDto)));
    }

    @Test
    @DisplayName("Get Department By Id Should Throw Exception")
    void getDepartmentByIdShouldThrowException() throws Exception {
        long departmentId = 1L;

        when(departmentService.getDepartmentById(departmentId))
                .thenThrow(new DepartmentNotFound("Department not found"));

        mockMvc.perform(get("/api/v1/departments/" + departmentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.path").value("/api/v1/departments/1"))
                .andExpect(jsonPath("$.message").value("Department not found"))
                .andExpect(jsonPath("$.statusCode").value(404));
    }


}