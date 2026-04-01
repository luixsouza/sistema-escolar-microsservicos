package matricula.servico;

import matricula.cliente.AlunoClient;
import matricula.cliente.DisciplinaClient;
import matricula.dto.MatriculaRequestDTO;
import matricula.dto.MatriculaResponseDTO;
import matricula.mapeador.MatriculaMapper;
import matricula.modelo.Matricula;
import matricula.repositorio.MatriculaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatriculaService {

    private final MatriculaRepository repositorio;
    private final MatriculaMapper mapeador;
    private final AlunoClient clienteAluno;
    private final DisciplinaClient clienteDisciplina;

    public MatriculaService(MatriculaRepository repositorio, MatriculaMapper mapeador,
                            AlunoClient clienteAluno, DisciplinaClient clienteDisciplina) {
        this.repositorio = repositorio;
        this.mapeador = mapeador;
        this.clienteAluno = clienteAluno;
        this.clienteDisciplina = clienteDisciplina;
    }

    public MatriculaResponseDTO criar(MatriculaRequestDTO dto) {
        clienteAluno.buscarPorId(dto.alunoId());
        clienteDisciplina.buscarPorId(dto.disciplinaId());
        Matricula matricula = mapeador.paraEntidade(dto);
        return mapeador.paraRespostaDTO(repositorio.save(matricula));
    }

    public List<MatriculaResponseDTO> buscarTodos() {
        return repositorio.findAll().stream()
                .map(mapeador::paraRespostaDTO)
                .toList();
    }

    public MatriculaResponseDTO buscarPorId(Long id) {
        Matricula matricula = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula não encontrada com id: " + id));
        return mapeador.paraRespostaDTO(matricula);
    }

    public void excluir(Long id) {
        if (!repositorio.existsById(id)) {
            throw new RuntimeException("Matrícula não encontrada com id: " + id);
        }
        repositorio.deleteById(id);
    }
}
