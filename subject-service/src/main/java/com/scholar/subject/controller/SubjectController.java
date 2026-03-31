package com.scholar.subject.controller;

import com.scholar.subject.dto.SubjectRequestDTO;
import com.scholar.subject.dto.SubjectResponseDTO;
import com.scholar.subject.service.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@Tag(name = "Disciplinas", description = "Gerenciamento de disciplinas")
public class SubjectController {

    private final SubjectService service;

    public SubjectController(SubjectService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Cadastrar disciplina", description = "Cadastra uma nova disciplina no sistema")
    @ApiResponse(responseCode = "201", description = "Disciplina cadastrada com sucesso")
    public ResponseEntity<SubjectResponseDTO> create(@Valid @RequestBody SubjectRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @GetMapping
    @Operation(summary = "Listar disciplinas", description = "Retorna todas as disciplinas cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de disciplinas")
    public ResponseEntity<List<SubjectResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar disciplina por ID", description = "Retorna uma disciplina pelo seu ID")
    @ApiResponse(responseCode = "200", description = "Disciplina encontrada")
    public ResponseEntity<SubjectResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar disciplina", description = "Atualiza os dados de uma disciplina existente")
    @ApiResponse(responseCode = "200", description = "Disciplina atualizada com sucesso")
    public ResponseEntity<SubjectResponseDTO> update(@PathVariable Long id, @Valid @RequestBody SubjectRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover disciplina", description = "Remove uma disciplina do sistema")
    @ApiResponse(responseCode = "204", description = "Disciplina removida com sucesso")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
