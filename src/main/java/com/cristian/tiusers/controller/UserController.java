package com.cristian.tiusers.controller;

import com.cristian.tiusers.dto.UserDto;
import com.cristian.tiusers.dto.UserProjectionDto;

import com.cristian.tiusers.repository.CompanyRepository;
import com.cristian.tiusers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping()
    public ResponseEntity<String> saveUser(@RequestBody UserDto user) {
        logger.debug("Saving user {}", user);
        userService.saveUser(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("user saved successfully");
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UserProjectionDto>> getUsersByCompany(
            @RequestParam Long companyId,
            @PageableDefault Pageable pageable
            ) {

        logger.debug("Getting users by company with id {}", companyId);

        return ResponseEntity.ok(userService.getUsersByCompany(Long.valueOf(companyId), pageable));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable(name = "id") Long id) {
        logger.debug("Deleting user with id {}", id);
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
