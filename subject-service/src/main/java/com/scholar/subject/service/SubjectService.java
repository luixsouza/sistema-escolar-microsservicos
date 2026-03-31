package com.scholar.subject.service;

import com.scholar.subject.dto.SubjectRequestDTO;
import com.scholar.subject.dto.SubjectResponseDTO;
import com.scholar.subject.mapper.SubjectMapper;
import com.scholar.subject.model.Subject;
import com.scholar.subject.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository repository;
    private final SubjectMapper mapper;

    public SubjectService(SubjectRepository repository, SubjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public SubjectResponseDTO create(SubjectRequestDTO dto) {
        Subject subject = mapper.toEntity(dto);
        return mapper.toResponseDTO(repository.save(subject));
    }

    public List<SubjectResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public SubjectResponseDTO findById(Long id) {
        Subject subject = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina nao encontrada com id: " + id));
        return mapper.toResponseDTO(subject);
    }

    public SubjectResponseDTO update(Long id, SubjectRequestDTO dto) {
        Subject subject = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina nao encontrada com id: " + id));
        mapper.updateEntity(subject, dto);
        return mapper.toResponseDTO(repository.save(subject));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Disciplina nao encontrada com id: " + id);
        }
        repository.deleteById(id);
    }
}
