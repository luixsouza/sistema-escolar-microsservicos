package com.escolar.disciplina.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "DisciplinaResponse", description = "Dados da disciplina retornados pela API")
public record DisciplinaResponseDTO(
        @Schema(description = "ID da disciplina", example = "1")
        Long id,

        @Schema(description = "Nome da disciplina", example = "Programacao Orientada a Objetos")
        String nome,

        @Schema(description = "Carga horaria em horas", example = "80")
        Integer cargaHoraria
) {}
