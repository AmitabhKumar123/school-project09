package com.school.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Class is required")
    private String className;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    // 🔹 ID Getter Setter (IMPORTANT)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // 🔹 Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 🔹 Class
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    // 🔹 Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}