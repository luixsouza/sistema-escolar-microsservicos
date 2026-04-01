package matricula.cliente;

import matricula.dto.DisciplinaResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "servico-disciplina")
public interface DisciplinaClient {

    @GetMapping("/api/disciplinas/{id}")
    DisciplinaResponseDTO buscarPorId(@PathVariable Long id);
}
