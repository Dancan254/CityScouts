package com.jobs.cityscouts.entity.companyEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobs.cityscouts.entity.Review;
import com.jobs.cityscouts.entity.jobEntity.Job;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String companyDescription;
    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobList;

    @OneToMany(mappedBy = "company")
    private List<Review> reviewList;

}
