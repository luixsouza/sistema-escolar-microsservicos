package com.scholar.enrollment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "EnrollmentRequest", description = "Dados para criacao de matricula")
public record EnrollmentRequestDTO(
        @Schema(description = "ID do aluno", example = "1")
        @NotNull
        Long studentId,

        @Schema(description = "ID da disciplina", example = "1")
        @NotNull
        Long subjectId
) {}
