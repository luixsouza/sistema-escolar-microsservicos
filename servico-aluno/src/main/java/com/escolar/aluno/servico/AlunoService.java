package com.escolar.aluno.servico;

import com.escolar.aluno.dto.AlunoRequestDTO;
import com.escolar.aluno.dto.AlunoResponseDTO;
import com.escolar.aluno.mapeador.AlunoMapper;
import com.escolar.aluno.modelo.Aluno;
import com.escolar.aluno.repositorio.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository repositorio;
    private final AlunoMapper mapeador;

    public AlunoService(AlunoRepository repositorio, AlunoMapper mapeador) {
        this.repositorio = repositorio;
        this.mapeador = mapeador;
    }

    public AlunoResponseDTO criar(AlunoRequestDTO dto) {
        Aluno aluno = mapeador.paraEntidade(dto);
        return mapeador.paraRespostaDTO(repositorio.save(aluno));
    }

    public List<AlunoResponseDTO> buscarTodos() {
        return repositorio.findAll().stream()
                .map(mapeador::paraRespostaDTO)
                .toList();
    }

    public AlunoResponseDTO buscarPorId(Long id) {
        Aluno aluno = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com id: " + id));
        return mapeador.paraRespostaDTO(aluno);
    }

    public AlunoResponseDTO atualizar(Long id, AlunoRequestDTO dto) {
        Aluno aluno = repositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado com id: " + id));
        mapeador.atualizarEntidade(aluno, dto);
        return mapeador.paraRespostaDTO(repositorio.save(aluno));
    }

    public void excluir(Long id) {
        if (!repositorio.existsById(id)) {
            throw new RuntimeException("Aluno não encontrado com id: " + id);
        }
        repositorio.deleteById(id);
    }
}
