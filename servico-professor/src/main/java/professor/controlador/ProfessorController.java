package professor.controlador;

import professor.dto.ProfessorRequestDTO;
import professor.dto.ProfessorResponseDTO;
import professor.servico.ProfessorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
@Tag(name = "Professores", description = "Gerenciamento de professores")
public class ProfessorController {

    private final ProfessorService servico;

    public ProfessorController(ProfessorService servico) {
        this.servico = servico;
    }

    @PostMapping
    @Operation(summary = "Cadastrar professor", description = "Cadastra um novo professor no sistema")
    @ApiResponse(responseCode = "201", description = "Professor cadastrado com sucesso")
    public ResponseEntity<ProfessorResponseDTO> criar(@Valid @RequestBody ProfessorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servico.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar professores", description = "Retorna todos os professores cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de professores")
    public ResponseEntity<List<ProfessorResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(servico.buscarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar professor por ID", description = "Retorna um professor pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Professor encontrado")
    public ResponseEntity<ProfessorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servico.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar professor", description = "Atualiza os dados de um professor existente")
    @ApiResponse(responseCode = "200", description = "Professor atualizado com sucesso")
    public ResponseEntity<ProfessorResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody ProfessorRequestDTO dto) {
        return ResponseEntity.ok(servico.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover professor", description = "Remove um professor do sistema")
    @ApiResponse(responseCode = "204", description = "Professor removido com sucesso")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        servico.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
