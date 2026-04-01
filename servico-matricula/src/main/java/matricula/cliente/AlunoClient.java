package matricula.cliente;

import matricula.dto.AlunoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servico-aluno")
public interface AlunoClient {

    @GetMapping("/api/alunos/{id}")
    AlunoResponseDTO buscarPorId(@PathVariable Long id);
}
