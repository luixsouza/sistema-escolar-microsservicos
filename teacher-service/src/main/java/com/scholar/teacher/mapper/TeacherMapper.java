package com.scholar.teacher.mapper;

import com.scholar.teacher.dto.TeacherRequestDTO;
import com.scholar.teacher.dto.TeacherResponseDTO;
import com.scholar.teacher.model.Teacher;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapper {

    public Teacher toEntity(TeacherRequestDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setName(dto.name());
        teacher.setEmail(dto.email());
        teacher.setDegree(dto.degree());
        return teacher;
    }

    public TeacherResponseDTO toResponseDTO(Teacher teacher) {
        return new TeacherResponseDTO(
                teacher.getId(),
                teacher.getName(),
                teacher.getEmail(),
                teacher.getDegree()
        );
    }

    public void updateEntity(Teacher teacher, TeacherRequestDTO dto) {
        teacher.setName(dto.name());
        teacher.setEmail(dto.email());
        teacher.setDegree(dto.degree());
    }
}
