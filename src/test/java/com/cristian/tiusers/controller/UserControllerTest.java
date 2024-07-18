package com.cristian.tiusers.controller;

import com.cristian.tiusers.dto.UserDto;
import com.cristian.tiusers.dto.UserProjectionDto;
import com.cristian.tiusers.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Save User Should Return Created")
    void saveUserShouldReturnCreated() throws Exception {
        UserDto userDto = new UserDto(
                "Test User",
                "Test User",
                "test address",
                "test position",
                "1234567890",
                "Test city",
                true,
                1L,
                2L
        );

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(content().string("user saved successfully"));
    }


    @Test
    @DisplayName("Delete User Should Return No Content")
    void deleteUserShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Get Users By Company Should Return Ok")
    void getUsersByCompanyShouldReturnOk() throws Exception {
        Long companyId = 1L;
        Page<UserProjectionDto> page = new PageImpl<>(Collections.singletonList(
                new UserProjectionDto(
                        1L,
                        "Test Name",
                        "Test lastname",
                        "1234567890",
                        true,
                        "HR Resources"
                )
        ));

        given(userService.getUsersByCompany(any(Long.class), any(Pageable.class))).willReturn(page);

        mockMvc.perform(get("/api/v1/users?companyId=" + companyId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].name").value("Test Name"))
                .andExpect(jsonPath("$.content[0].departmentName").value("HR Resources"))
                .andExpect(jsonPath("$.content[0].telephone").value("1234567890"));
    }


}