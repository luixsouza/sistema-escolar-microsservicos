package com.scholar.enrollment.controller;

import com.scholar.enrollment.dto.MatriculaRequestDTO;
import com.scholar.enrollment.dto.MatriculaResponseDTO;
import com.scholar.enrollment.service.MatriculaService;
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
@Tag(name = "Matriculas", description = "Gerenciamento de matriculas em disciplinas")
public class MatriculaController {

    private final MatriculaService service;

    public MatriculaController(MatriculaService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Realizar matricula", description = "Matricula um aluno em uma disciplina (valida existencia via Feign)")
    @ApiResponse(responseCode = "201", description = "Matricula realizada com sucesso")
    public ResponseEntity<MatriculaResponseDTO> create(@Valid @RequestBody MatriculaRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    @Operation(summary = "Listar matriculas", description = "Retorna todas as matriculas realizadas")
    @ApiResponse(responseCode = "200", description = "Lista de matriculas")
    public ResponseEntity<List<MatriculaResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar matricula por ID", description = "Retorna uma matricula pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Matricula encontrada")
    public ResponseEntity<MatriculaResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancelar matricula", description = "Remove uma matricula do sistema")
    @ApiResponse(responseCode = "204", description = "Matricula cancelada com sucesso")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
