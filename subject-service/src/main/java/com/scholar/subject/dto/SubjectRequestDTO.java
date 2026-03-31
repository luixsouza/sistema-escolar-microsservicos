package com.scholar.subject.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(name = "SubjectRequest", description = "Dados para criacao/atualizacao de disciplina")
public record SubjectRequestDTO(
        @Schema(description = "Nome da disciplina", example = "Programacao Orientada a Objetos")
        @NotBlank
        String name,

        @Schema(description = "Carga horaria em horas", example = "80")
        @NotNull @Positive
        Integer workload
) {}
