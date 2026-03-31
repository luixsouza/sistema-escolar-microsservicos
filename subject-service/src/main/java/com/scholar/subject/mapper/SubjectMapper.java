package com.scholar.subject.mapper;

import com.scholar.subject.dto.SubjectRequestDTO;
import com.scholar.subject.dto.SubjectResponseDTO;
import com.scholar.subject.model.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {

    public Subject toEntity(SubjectRequestDTO dto) {
        Subject subject = new Subject();
        subject.setName(dto.name());
        subject.setWorkload(dto.workload());
        return subject;
    }

    public SubjectResponseDTO toResponseDTO(Subject subject) {
        return new SubjectResponseDTO(
                subject.getId(),
                subject.getName(),
                subject.getWorkload()
        );
    }

    public void updateEntity(Subject subject, SubjectRequestDTO dto) {
        subject.setName(dto.name());
        subject.setWorkload(dto.workload());
    }
}
