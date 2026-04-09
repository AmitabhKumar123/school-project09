package com.school.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class StudentRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Class is required")
    private String className;

    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;

    // 🔹 Getter Setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}