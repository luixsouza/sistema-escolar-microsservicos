package com.scholar.enrollment.client;

import com.scholar.enrollment.dto.DisciplinaResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "subject-service")
public interface DisciplinaClient {

    @GetMapping("/api/disciplinas/{id}")
    DisciplinaResponseDTO findById(@PathVariable Long id);
}
