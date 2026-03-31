package com.scholar.subject.mapper;

import com.scholar.subject.dto.DisciplinaRequestDTO;
import com.scholar.subject.dto.DisciplinaResponseDTO;
import com.scholar.subject.model.Disciplina;
import org.springframework.stereotype.Component;

@Component
public class DisciplinaMapper {

    public Disciplina toEntity(DisciplinaRequestDTO dto) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.nome());
        disciplina.setCargaHoraria(dto.cargaHoraria());
        return disciplina;
    }

    public DisciplinaResponseDTO toResponseDTO(Disciplina disciplina) {
        return new DisciplinaResponseDTO(
                disciplina.getId(),
                disciplina.getNome(),
                disciplina.getCargaHoraria()
        );
    }

    public void updateEntity(Disciplina disciplina, DisciplinaRequestDTO dto) {
        disciplina.setNome(dto.nome());
        disciplina.setCargaHoraria(dto.cargaHoraria());
    }
}
