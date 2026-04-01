package matricula.mapeador;

import matricula.dto.MatriculaRequestDTO;
import matricula.dto.MatriculaResponseDTO;
import matricula.modelo.Matricula;
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
