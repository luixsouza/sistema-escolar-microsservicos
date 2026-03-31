package com.scholar.student.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "StudentResponse", description = "Dados do aluno retornados pela API")
public record StudentResponseDTO(
        @Schema(description = "ID do aluno", example = "1")
        Long id,

        @Schema(description = "Nome completo do aluno", example = "Joao Silva")
        String name,

        @Schema(description = "Email institucional", example = "joao.silva@aluno.ifsp.edu.br")
        String email,

        @Schema(description = "Numero de matricula", example = "2024001")
        String registration
) {}
