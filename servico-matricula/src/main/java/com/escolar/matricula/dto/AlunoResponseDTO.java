package com.escolar.matricula.dto;

public record AlunoResponseDTO(
        Long id,
        String nome,
        String email,
        String matricula
) {}
