package com.scholar.enrollment.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "EnrollmentResponse", description = "Dados da matricula retornados pela API")
public record EnrollmentResponseDTO(
        @Schema(description = "ID da matricula", example = "1")
        Long id,

        @Schema(description = "ID do aluno", example = "1")
        Long studentId,

        @Schema(description = "ID da disciplina", example = "1")
        Long subjectId
) {}
