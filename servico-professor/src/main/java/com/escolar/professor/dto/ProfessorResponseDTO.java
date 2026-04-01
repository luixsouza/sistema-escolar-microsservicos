package com.escolar.professor.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ProfessorResponse", description = "Dados do professor retornados pela API")
public record ProfessorResponseDTO(
        @Schema(description = "ID do professor", example = "1")
        Long id,

        @Schema(description = "Nome completo do professor", example = "Maria Oliveira")
        String nome,

        @Schema(description = "Email institucional", example = "maria.oliveira@ifsp.edu.br")
        String email,

        @Schema(description = "Titulacao academica", example = "Doutorado em Ciencia da Computacao")
        String titulacao
) {}
