package com.scholar.enrollment.service;

import com.scholar.enrollment.client.StudentClient;
import com.scholar.enrollment.client.SubjectClient;
import com.scholar.enrollment.dto.EnrollmentRequestDTO;
import com.scholar.enrollment.dto.EnrollmentResponseDTO;
import com.scholar.enrollment.mapper.EnrollmentMapper;
import com.scholar.enrollment.model.Enrollment;
import com.scholar.enrollment.repository.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository repository;
    private final EnrollmentMapper mapper;
    private final StudentClient studentClient;
    private final SubjectClient subjectClient;

    public EnrollmentService(EnrollmentRepository repository, EnrollmentMapper mapper,
                             StudentClient studentClient, SubjectClient subjectClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.studentClient = studentClient;
        this.subjectClient = subjectClient;
    }

    public EnrollmentResponseDTO create(EnrollmentRequestDTO dto) {
        studentClient.findById(dto.studentId());
        subjectClient.findById(dto.subjectId());
        Enrollment enrollment = mapper.toEntity(dto);
        return mapper.toResponseDTO(repository.save(enrollment));
    }

    public List<EnrollmentResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public EnrollmentResponseDTO findById(Long id) {
        Enrollment enrollment = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matricula nao encontrada com id: " + id));
        return mapper.toResponseDTO(enrollment);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Matricula nao encontrada com id: " + id);
        }
        repository.deleteById(id);
    }
}
