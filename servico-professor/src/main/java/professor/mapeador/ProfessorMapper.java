package professor.mapeador;

import professor.dto.ProfessorRequestDTO;
import professor.dto.ProfessorResponseDTO;
import professor.modelo.Professor;
import org.springframework.stereotype.Component;

@Component
public class ProfessorMapper {

    public Professor paraEntidade(ProfessorRequestDTO dto) {
        Professor professor = new Professor();
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTitulacao(dto.titulacao());
        return professor;
    }

    public ProfessorResponseDTO paraRespostaDTO(Professor professor) {
        return new ProfessorResponseDTO(
                professor.getId(),
                professor.getNome(),
                professor.getEmail(),
                professor.getTitulacao()
        );
    }

    public void atualizarEntidade(Professor professor, ProfessorRequestDTO dto) {
        professor.setNome(dto.nome());
        professor.setEmail(dto.email());
        professor.setTitulacao(dto.titulacao());
    }
}
