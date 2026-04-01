package disciplina.servico;

import disciplina.dto.DisciplinaRequestDTO;
import disciplina.dto.DisciplinaResponseDTO;
import disciplina.mapeador.DisciplinaMapper;
import disciplina.modelo.Disciplina;
import disciplina.repositorio.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    private final DisciplinaRepository repositorio;
    private final DisciplinaMapper mapeador;

    public DisciplinaService(DisciplinaRepository repositorio, DisciplinaMapper mapeador) {
        this.repositorio = repositorio;
        this.mapeador = mapeador;
    }

    public DisciplinaResponseDTO criar(DisciplinaRequestDTO dto) {
        Disciplina disciplina = mapeador.paraEntidade(dto);
        return mapeador.paraRespostaDTO(repositorio.save(disciplina));
    }

    public List<DisciplinaResponseDTO> buscarTodos() {
        return repositorio.findAll().stream()
                .map(mapeador::paraRespostaDTO)
                .toList();
    }

    public DisciplinaResponseDTO buscarPorId(Long id) {
        Disciplina disciplina = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada com id: " + id));
        return mapeador.paraRespostaDTO(disciplina);
    }

    public DisciplinaResponseDTO atualizar(Long id, DisciplinaRequestDTO dto) {
        Disciplina disciplina = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada com id: " + id));
        mapeador.atualizarEntidade(disciplina, dto);
        return mapeador.paraRespostaDTO(repositorio.save(disciplina));
    }

    public void excluir(Long id) {
        if (!repositorio.existsById(id)) {
            throw new RuntimeException("Disciplina não encontrada com id: " + id);
        }
        repositorio.deleteById(id);
    }
}
