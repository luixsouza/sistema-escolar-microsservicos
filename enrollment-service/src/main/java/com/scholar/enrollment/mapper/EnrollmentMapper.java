package com.scholar.enrollment.mapper;

import com.scholar.enrollment.dto.EnrollmentRequestDTO;
import com.scholar.enrollment.dto.EnrollmentResponseDTO;
import com.scholar.enrollment.model.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public Enrollment toEntity(EnrollmentRequestDTO dto) {
        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(dto.studentId());
        enrollment.setSubjectId(dto.subjectId());
        return enrollment;
    }

    public EnrollmentResponseDTO toResponseDTO(Enrollment enrollment) {
        return new EnrollmentResponseDTO(
                enrollment.getId(),
                enrollment.getStudentId(),
                enrollment.getSubjectId()
        );
    }
}
