package com.scholar.teacher.mapper;

import com.scholar.teacher.dto.ProfessorRequestDTO;
import com.scholar.teacher.dto.ProfessorResponseDTO;
import com.scholar.teacher.model.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public Professor toEntity(ProfessorRequestDTO dto) {
        Professor professor = new Professor();
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTitulacao(dto.titulacao());
        return professor;
    }

    public ProfessorResponseDTO toResponseDTO(Professor professor) {
        return new ProfessorResponseDTO(
                professor.getId(),
                professor.getNome(),
                professor.getEmail(),
                professor.getTitulacao()
        );
    }

    public void updateEntity(Professor professor, ProfessorRequestDTO dto) {
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTitulacao(dto.titulacao());
    }
}
