package com.scholar.subject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer workload;

    public Subject() {}

    public Subject(Long id, String name, Integer workload) {
        this.id = id;
        this.name = name;
        this.workload = workload;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getWorkload() { return workload; }
    public void setWorkload(Integer workload) { this.workload = workload; }
}
