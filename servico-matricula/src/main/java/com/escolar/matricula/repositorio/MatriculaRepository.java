package com.escolar.matricula.repositorio;

import com.escolar.matricula.modelo.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
}
