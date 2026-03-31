package com.scholar.subject.service;

import com.scholar.subject.dto.DisciplinaRequestDTO;
import com.scholar.subject.dto.DisciplinaResponseDTO;
import com.scholar.subject.mapper.DisciplinaMapper;
import com.scholar.subject.model.Disciplina;
import com.scholar.subject.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    private final DisciplinaRepository repository;
    private final DisciplinaMapper mapper;

    public DisciplinaService(DisciplinaRepository repository, DisciplinaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public DisciplinaResponseDTO create(DisciplinaRequestDTO dto) {
        Disciplina disciplina = mapper.toEntity(dto);
        return mapper.toResponseDTO(repository.save(disciplina));
    }

    public List<DisciplinaResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public DisciplinaResponseDTO findById(Long id) {
        Disciplina disciplina = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina nao encontrada com id: " + id));
        return mapper.toResponseDTO(disciplina);
    }

    public DisciplinaResponseDTO update(Long id, DisciplinaRequestDTO dto) {
        Disciplina disciplina = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina nao encontrada com id: " + id));
        mapper.updateEntity(disciplina, dto);
        return mapper.toResponseDTO(repository.save(disciplina));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Disciplina nao encontrada com id: " + id);
        }
        repository.deleteById(id);
    }
}
