package com.scholar.teacher.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "TeacherResponse", description = "Dados do professor retornados pela API")
public record TeacherResponseDTO(
        @Schema(description = "ID do professor", example = "1")
        Long id,

        @Schema(description = "Nome completo do professor", example = "Maria Oliveira")
        String name,

        @Schema(description = "Email institucional", example = "maria.oliveira@ifsp.edu.br")
        String email,

        @Schema(description = "Titulacao academica", example = "Doutorado em Ciencia da Computacao")
        String degree
) {}
