package com.escolar.matricula.mapeador;

import com.escolar.matricula.dto.MatriculaRequestDTO;
import com.escolar.matricula.dto.MatriculaResponseDTO;
import com.escolar.matricula.modelo.Matricula;
import org.springframework.stereotype.Component;

@Component
public class MatriculaMapper {

    public Matricula paraEntidade(MatriculaRequestDTO dto) {
        Matricula matricula = new Matricula();
        matricula.setAlunoId(dto.alunoId());
        matricula.setDisciplinaId(dto.disciplinaId());
        return matricula;
    }

    public MatriculaResponseDTO paraRespostaDTO(Matricula matricula) {
        return new MatriculaResponseDTO(
                matricula.getId(),
                matricula.getAlunoId(),
                matricula.getDisciplinaId()
        );
    }
}
