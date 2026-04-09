package com.school.service;

import com.school.dto.StudentRequestDTO;
import com.school.dto.StudentResponseDTO;
import com.school.model.Student;
import com.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    // ✅ DTO → Entity → DTO
    public StudentResponseDTO saveStudent(StudentRequestDTO dto) {

        Student s = new Student();
        s.setName(dto.getName());
        s.setClassName(dto.getClassName());
        s.setEmail(dto.getEmail());

        Student saved = repo.save(s);

        return new StudentResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getClassName(),
                saved.getEmail()
        );
    }

    // ✅ GET ALL
    public List<StudentResponseDTO> getAllStudents() {
        return repo.findAll().stream()
                .map(s -> new StudentResponseDTO(
                        s.getId(),
                        s.getName(),
                        s.getClassName(),
                        s.getEmail()
                ))
                .toList();
    }
    
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {

        // 🔍 Step 1: Student find करो
        Student s = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // 🔄 Step 2: Update values
        s.setName(dto.getName());
        s.setClassName(dto.getClassName());
        s.setEmail(dto.getEmail());

        // 💾 Step 3: Save
        Student updated = repo.save(s);

        // 🔁 Step 4: Return DTO
        return new StudentResponseDTO(
                updated.getId(),
                updated.getName(),
                updated.getClassName(),
                updated.getEmail()
        );
    }
}