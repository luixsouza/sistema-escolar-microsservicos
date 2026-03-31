package com.scholar.enrollment.service;

import com.scholar.enrollment.client.AlunoClient;
import com.scholar.enrollment.client.DisciplinaClient;
import com.scholar.enrollment.dto.MatriculaRequestDTO;
import com.scholar.enrollment.dto.MatriculaResponseDTO;
import com.scholar.enrollment.mapper.MatriculaMapper;
import com.scholar.enrollment.model.Matricula;
import com.scholar.enrollment.repository.MatriculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {

    private final MatriculaRepository repository;
    private final MatriculaMapper mapper;
    private final AlunoClient alunoClient;
    private final DisciplinaClient disciplinaClient;

    public MatriculaService(MatriculaRepository repository, MatriculaMapper mapper,
                            AlunoClient alunoClient, DisciplinaClient disciplinaClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.alunoClient = alunoClient;
        this.disciplinaClient = disciplinaClient;
    }

    public MatriculaResponseDTO create(MatriculaRequestDTO dto) {
        alunoClient.findById(dto.alunoId());
        disciplinaClient.findById(dto.disciplinaId());
        Matricula matricula = mapper.toEntity(dto);
        return mapper.toResponseDTO(repository.save(matricula));
    }

    public List<MatriculaResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponseDTO)
                .toList();
    }

    public MatriculaResponseDTO findById(Long id) {
        Matricula matricula = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matricula nao encontrada com id: " + id));
        return mapper.toResponseDTO(matricula);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Matricula nao encontrada com id: " + id);
        }
        repository.deleteById(id);
    }
}
