package com.scholar.enrollment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "MatriculaRequest", description = "Dados para criacao de matricula")
public record MatriculaRequestDTO(
        @Schema(description = "ID do aluno", example = "1")
        @NotNull
        Long alunoId,

        @Schema(description = "ID da disciplina", example = "1")
        @NotNull
        Long disciplinaId
) {}
