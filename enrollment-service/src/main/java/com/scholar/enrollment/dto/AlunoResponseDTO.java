package com.scholar.enrollment.dto;

public record AlunoResponseDTO(
        Long id,
        String nome,
        String email,
        String matricula
) {}
