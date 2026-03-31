package com.scholar.enrollment.client;

import com.scholar.enrollment.dto.AlunoResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "student-service")
public interface AlunoClient {

    @GetMapping("/api/alunos/{id}")
    AlunoResponseDTO findById(@PathVariable Long id);
}
