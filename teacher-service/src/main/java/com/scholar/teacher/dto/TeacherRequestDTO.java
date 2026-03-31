package com.scholar.teacher.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "TeacherRequest", description = "Dados para criacao/atualizacao de professor")
public record TeacherRequestDTO(
        @Schema(description = "Nome completo do professor", example = "Maria Oliveira")
        @NotBlank
        String name,

        @Schema(description = "Email institucional", example = "maria.oliveira@ifsp.edu.br")
        @NotBlank @Email
        String email,

        @Schema(description = "Titulacao academica", example = "Doutorado em Ciencia da Computacao")
        @NotBlank
        String degree
) {}
