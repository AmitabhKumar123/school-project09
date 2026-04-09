package com.school.service;

import com.school.model.Teacher;
import com.school.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository repo;

    public TeacherService(TeacherRepository repo) {
        this.repo = repo;
    }

    public Teacher save(Teacher t) {
        return repo.save(t);
    }

    public List<Teacher> getAll() {
        return repo.findAll();
    }
}