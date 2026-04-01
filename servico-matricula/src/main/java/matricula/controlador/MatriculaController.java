package matricula.controlador;

import matricula.dto.MatriculaRequestDTO;
import matricula.dto.MatriculaResponseDTO;
import matricula.servico.MatriculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
@Tag(name = "Matrículas", description = "Gerenciamento de matrículas em disciplinas")
public class MatriculaController {

    private final MatriculaService servico;

    public MatriculaController(MatriculaService servico) {
        this.servico = servico;
    }

    @PostMapping
    @Operation(summary = "Realizar matrícula", description = "Matricula um aluno em uma disciplina (valida existência via Feign)")
    @ApiResponse(responseCode = "201", description = "Matrícula realizada com sucesso")
    public ResponseEntity<MatriculaResponseDTO> criar(@Valid @RequestBody MatriculaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(servico.criar(dto));
    }

    @GetMapping
    @Operation(summary = "Listar matrículas", description = "Retorna todas as matrículas realizadas")
    @ApiResponse(responseCode = "200", description = "Lista de matrículas")
    public ResponseEntity<List<MatriculaResponseDTO>> buscarTodos() {
        return ResponseEntity.ok(servico.buscarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar matrícula por ID", description = "Retorna uma matrícula pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Matrícula encontrada")
    public ResponseEntity<MatriculaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servico.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancelar matrícula", description = "Remove uma matrícula do sistema")
    @ApiResponse(responseCode = "204", description = "Matrícula cancelada com sucesso")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        servico.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
