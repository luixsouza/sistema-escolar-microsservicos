package aluno.mapeador;

import aluno.dto.AlunoRequestDTO;
import aluno.dto.AlunoResponseDTO;
import aluno.modelo.Aluno;
import org.springframework.stereotype.Component;

@Component
public class AlunoMapper {

    public Aluno paraEntidade(AlunoRequestDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        return aluno;
    }

    public AlunoResponseDTO paraRespostaDTO(Aluno aluno) {
        return new AlunoResponseDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getMatricula()
        );
    }

    public void atualizarEntidade(Aluno aluno, AlunoRequestDTO dto) {
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
    }
}
