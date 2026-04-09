package com.school.controller;

import com.school.model.Teacher;
import com.school.service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @PostMapping
    public Teacher add(@RequestBody Teacher t) {
        return service.save(t);
    }

    @GetMapping
    public List<Teacher> getAll() {
        return service.getAll();
    }
}