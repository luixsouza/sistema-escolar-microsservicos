package com.scholar.student.service;

import com.scholar.student.dto.StudentRequestDTO;
import com.scholar.student.dto.StudentResponseDTO;
import com.scholar.student.mapper.StudentMapper;
import com.scholar.student.model.Student;
import com.scholar.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final StudentMapper mapper;

    public StudentService(StudentRepository repository, StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public StudentResponseDTO create(StudentRequestDTO dto) {
        Student student = mapper.toEntity(dto);
        return mapper.toResponseDTO(repository.save(student));
    }

    public List<StudentResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public StudentResponseDTO findById(Long id) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno nao encontrado com id: " + id));
        return mapper.toResponseDTO(student);
    }

    public StudentResponseDTO update(Long id, StudentRequestDTO dto) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno nao encontrado com id: " + id));
        mapper.updateEntity(student, dto);
        return mapper.toResponseDTO(repository.save(student));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Aluno nao encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
