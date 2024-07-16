package com.cristian.tiusers.controller;

import com.cristian.tiusers.dto.DepartmentDto;
import com.cristian.tiusers.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<String> saveDepartment(@RequestBody @Valid DepartmentDto departmentDto) {
        departmentService.saveDepartment(departmentDto);
        return ResponseEntity.
                status(HttpStatus.CREATED)
                .body("department saved successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDepartment( @PathVariable Long id, @RequestBody @Valid DepartmentDto departmentDto) {
        departmentService.updateDepartment(id, departmentDto);
        return ResponseEntity.ok("department updated successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById( @PathVariable Long id) {
        DepartmentDto department = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }


}
