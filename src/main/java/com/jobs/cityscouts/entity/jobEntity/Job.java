package com.jobs.cityscouts.entity.jobEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobs.cityscouts.entity.companyEntity.Company;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Long minSalary;
    @Column(nullable = false)
    private Long maxSalary;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime editedAt;

    @ManyToOne
    private Company company;
    }
