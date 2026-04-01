package disciplina.controlador;

import disciplina.dto.DisciplinaRequestDTO;
import disciplina.dto.DisciplinaResponseDTO;
import disciplina.servico.DisciplinaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
@Tag(name = "Disciplinas", description = "Gerenciamento de disciplinas")
public class DisciplinaController {

    private final DisciplinaService servico;

    public DisciplinaController(DisciplinaService servico) {
        this.servico = servico;
    }

    @PostMapping
    @Operation(summary = "Cadastrar disciplina", description = "Cadastra uma nova disciplina no sistema")
    @ApiResponse(responseCode = "201", description = "Disciplina cadastrada com sucesso")
    public ResponseEntity<DisciplinaResponseDTO> criar(@Valid @RequestBody DisciplinaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servico.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar disciplinas", description = "Retorna todas as disciplinas cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de disciplinas")
    public ResponseEntity<List<DisciplinaResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(servico.buscarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar disciplina por ID", description = "Retorna uma disciplina pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Disciplina encontrada")
    public ResponseEntity<DisciplinaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servico.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar disciplina", description = "Atualiza os dados de uma disciplina existente")
    @ApiResponse(responseCode = "200", description = "Disciplina atualizada com sucesso")
    public ResponseEntity<DisciplinaResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody DisciplinaRequestDTO dto) {
        return ResponseEntity.ok(servico.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover disciplina", description = "Remove uma disciplina do sistema")
    @ApiResponse(responseCode = "204", description = "Disciplina removida com sucesso")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        servico.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
