package com.scholar.subject.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "SubjectResponse", description = "Dados da disciplina retornados pela API")
public record SubjectResponseDTO(
        @Schema(description = "ID da disciplina", example = "1")
        Long id,

        @Schema(description = "Nome da disciplina", example = "Programacao Orientada a Objetos")
        String name,

        @Schema(description = "Carga horaria em horas", example = "80")
        Integer workload
) {}
