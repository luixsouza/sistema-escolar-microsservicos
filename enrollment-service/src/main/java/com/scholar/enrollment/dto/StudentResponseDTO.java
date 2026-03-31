package com.scholar.enrollment.dto;

public record StudentResponseDTO(
        Long id,
        String name,
        String email,
        String registration
) {}
