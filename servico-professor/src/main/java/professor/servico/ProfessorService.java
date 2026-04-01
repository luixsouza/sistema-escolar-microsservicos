package professor.servico;

import professor.dto.ProfessorRequestDTO;
import professor.dto.ProfessorResponseDTO;
import professor.mapeador.ProfessorMapper;
import professor.modelo.Professor;
import professor.repositorio.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository repositorio;
    private final ProfessorMapper mapeador;

    public ProfessorService(ProfessorRepository repositorio, ProfessorMapper mapeador) {
        this.repositorio = repositorio;
        this.mapeador = mapeador;
    }

    public ProfessorResponseDTO criar(ProfessorRequestDTO dto) {
        Professor professor = mapeador.paraEntidade(dto);
        return mapeador.paraRespostaDTO(repositorio.save(professor));
    }

    public List<ProfessorResponseDTO> buscarTodos() {
        return repositorio.findAll().stream()
                .map(mapeador::paraRespostaDTO)
                .toList();
    }

    public ProfessorResponseDTO buscarPorId(Long id) {
        Professor professor = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com id: " + id));
        return mapeador.paraRespostaDTO(professor);
    }

    public ProfessorResponseDTO atualizar(Long id, ProfessorRequestDTO dto) {
        Professor professor = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com id: " + id));
        mapeador.atualizarEntidade(professor, dto);
        return mapeador.paraRespostaDTO(repositorio.save(professor));
    }

    public void excluir(Long id) {
        if (!repositorio.existsById(id)) {
            throw new RuntimeException("Professor não encontrado com id: " + id);
        }
        repositorio.deleteById(id);
    }
}
