package com.escolar.professor.repositorio;

import com.escolar.professor.modelo.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
