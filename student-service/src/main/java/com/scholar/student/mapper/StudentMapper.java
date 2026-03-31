package com.scholar.student.mapper;

import com.scholar.student.dto.StudentRequestDTO;
import com.scholar.student.dto.StudentResponseDTO;
import com.scholar.student.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public Student toEntity(StudentRequestDTO dto) {
        Student student = new Student();
        student.setName(dto.name());
        student.setEmail(dto.email());
        student.setRegistration(dto.registration());
        return student;
    }

    public StudentResponseDTO toResponseDTO(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getRegistration()
        );
    }

    public void updateEntity(Student student, StudentRequestDTO dto) {
        student.setName(dto.name());
        student.setEmail(dto.email());
        student.setRegistration(dto.registration());
    }
}
