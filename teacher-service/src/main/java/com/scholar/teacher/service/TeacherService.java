package com.scholar.teacher.service;

import com.scholar.teacher.dto.TeacherRequestDTO;
import com.scholar.teacher.dto.TeacherResponseDTO;
import com.scholar.teacher.mapper.TeacherMapper;
import com.scholar.teacher.model.Teacher;
import com.scholar.teacher.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository repository;
    private final TeacherMapper mapper;

    public TeacherService(TeacherRepository repository, TeacherMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public TeacherResponseDTO create(TeacherRequestDTO dto) {
        Teacher teacher = mapper.toEntity(dto);
        return mapper.toResponseDTO(repository.save(teacher));
    }

    public List<TeacherResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public TeacherResponseDTO findById(Long id) {
        Teacher teacher = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor nao encontrado com id: " + id));
        return mapper.toResponseDTO(teacher);
    }

    public TeacherResponseDTO update(Long id, TeacherRequestDTO dto) {
        Teacher teacher = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor nao encontrado com id: " + id));
        mapper.updateEntity(teacher, dto);
        return mapper.toResponseDTO(repository.save(teacher));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Professor nao encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
