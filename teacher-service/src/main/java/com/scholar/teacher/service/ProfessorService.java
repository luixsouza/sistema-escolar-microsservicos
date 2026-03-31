package com.scholar.teacher.service;

import com.scholar.teacher.dto.ProfessorRequestDTO;
import com.scholar.teacher.dto.ProfessorResponseDTO;
import com.scholar.teacher.mapper.ProfessorMapper;
import com.scholar.teacher.model.Professor;
import com.scholar.teacher.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;
    private final ProfessorMapper mapper;

    public ProfessorService(ProfessorRepository repository, ProfessorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public ProfessorResponseDTO create(ProfessorRequestDTO dto) {
        Professor professor = mapper.toEntity(dto);
        return mapper.toResponseDTO(repository.save(professor));
    }

    public List<ProfessorResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public ProfessorResponseDTO findById(Long id) {
        Professor professor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor nao encontrado com id: " + id));
        return mapper.toResponseDTO(professor);
    }

    public ProfessorResponseDTO update(Long id, ProfessorRequestDTO dto) {
        Professor professor = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor nao encontrado com id: " + id));
        mapper.updateEntity(professor, dto);
        return mapper.toResponseDTO(repository.save(professor));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Professor nao encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}
