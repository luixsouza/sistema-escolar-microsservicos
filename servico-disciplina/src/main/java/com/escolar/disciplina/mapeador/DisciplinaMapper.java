package com.escolar.disciplina.mapeador;

import com.escolar.disciplina.dto.DisciplinaRequestDTO;
import com.escolar.disciplina.dto.DisciplinaResponseDTO;
import com.escolar.disciplina.modelo.Disciplina;
import org.springframework.stereotype.Component;

@Component
public class DisciplinaMapper {

    public Disciplina paraEntidade(DisciplinaRequestDTO dto) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.nome());
        disciplina.setCargaHoraria(dto.cargaHoraria());
        return disciplina;
    }

    public DisciplinaResponseDTO paraRespostaDTO(Disciplina disciplina) {
        return new DisciplinaResponseDTO(
                disciplina.getId(),
                disciplina.getNome(),
                disciplina.getCargaHoraria()
        );
    }

    public void atualizarEntidade(Disciplina disciplina, DisciplinaRequestDTO dto) {
        disciplina.setNome(dto.nome());
        disciplina.setCargaHoraria(dto.cargaHoraria());
    }
}
