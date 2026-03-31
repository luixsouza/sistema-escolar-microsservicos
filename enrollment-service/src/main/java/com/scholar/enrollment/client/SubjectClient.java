package com.scholar.enrollment.client;

import com.scholar.enrollment.dto.SubjectResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "subject-service")
public interface SubjectClient {

    @GetMapping("/api/subjects/{id}")
    SubjectResponseDTO findById(@PathVariable Long id);
}
