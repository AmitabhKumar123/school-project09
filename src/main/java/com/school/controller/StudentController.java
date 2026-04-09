package com.school.controller;

import com.school.dto.ApiResponse;
import com.school.dto.StudentRequestDTO;
import com.school.dto.StudentResponseDTO;
import com.school.service.StudentService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // ✅ POST
    @PostMapping
    public ApiResponse<StudentResponseDTO> addStudent(
            @Valid @RequestBody StudentRequestDTO dto) {

        return new ApiResponse<>(200, "Student added", service.saveStudent(dto));
    }

    // ✅ GET ALL
    @GetMapping
    public ApiResponse<List<StudentResponseDTO>> getAllStudents() {
        return new ApiResponse<>(200, "Success", service.getAllStudents());
    }
    
    @PutMapping("/{id}")
    public ApiResponse<StudentResponseDTO> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentRequestDTO dto) {

        return new ApiResponse<>(
                200,
                "Student updated",
                service.updateStudent(id, dto)
        );
    }
}