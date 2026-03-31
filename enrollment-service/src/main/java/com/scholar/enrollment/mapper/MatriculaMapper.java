package com.scholar.enrollment.mapper;

import com.scholar.enrollment.dto.MatriculaRequestDTO;
import com.scholar.enrollment.dto.MatriculaResponseDTO;
import com.scholar.enrollment.model.Matricula;
import org.springframework.stereotype.Component;

@Component
public class MatriculaMapper {

    public Matricula toEntity(MatriculaRequestDTO dto) {
        Matricula matricula = new Matricula();
        matricula.setAlunoId(dto.alunoId());
        matricula.setDisciplinaId(dto.disciplinaId());
        return matricula;
    }

    public MatriculaResponseDTO toResponseDTO(Matricula matricula) {
        return new MatriculaResponseDTO(
                matricula.getId(),
                matricula.getAlunoId(),
                matricula.getDisciplinaId()
        );
    }
}
