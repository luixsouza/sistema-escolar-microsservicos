package professor.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "ProfessorRequest", description = "Dados para criacao/atualizacao de professor")
public record ProfessorRequestDTO(
        @Schema(description = "Nome completo do professor", example = "Maria Oliveira")
        @NotBlank
        String nome,

        @Schema(description = "Email institucional", example = "maria.oliveira@ifsp.edu.br")
        @NotBlank @Email
        String email,

        @Schema(description = "Titulacao academica", example = "Doutorado em Ciencia da Computacao")
        @NotBlank
        String titulacao
) {}
