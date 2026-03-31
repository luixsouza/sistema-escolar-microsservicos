package com.scholar.enrollment.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "MatriculaResponse", description = "Dados da matricula retornados pela API")
public record MatriculaResponseDTO(
        @Schema(description = "ID da matricula", example = "1")
        Long id,

        @Schema(description = "ID do aluno", example = "1")
        Long alunoId,

        @Schema(description = "ID da disciplina", example = "1")
        Long disciplinaId
) {}
