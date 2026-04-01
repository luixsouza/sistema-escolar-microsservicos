package com.escolar.disciplina.repositorio;

import com.escolar.disciplina.modelo.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
}
