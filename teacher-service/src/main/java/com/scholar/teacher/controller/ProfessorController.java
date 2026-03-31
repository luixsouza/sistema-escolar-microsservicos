package com.scholar.teacher.controller;

import com.scholar.teacher.dto.ProfessorRequestDTO;
import com.scholar.teacher.dto.ProfessorResponseDTO;
import com.scholar.teacher.service.ProfessorService;
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

    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cadastrar professor", description = "Cadastra um novo professor no sistema")
    @ApiResponse(responseCode = "201", description = "Professor cadastrado com sucesso")
    public ResponseEntity<ProfessorResponseDTO> create(@Valid @RequestBody ProfessorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    @Operation(summary = "Listar professores", description = "Retorna todos os professores cadastrados")
    @ApiResponse(responseCode = "200", description = "Lista de professores")
    public ResponseEntity<List<ProfessorResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar professor por ID", description = "Retorna um professor pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Professor encontrado")
    public ResponseEntity<ProfessorResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar professor", description = "Atualiza os dados de um professor existente")
    @ApiResponse(responseCode = "200", description = "Professor atualizado com sucesso")
    public ResponseEntity<ProfessorResponseDTO> update(@PathVariable Long id, @Valid @RequestBody ProfessorRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover professor", description = "Remove um professor do sistema")
    @ApiResponse(responseCode = "204", description = "Professor removido com sucesso")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
