package com.jobs.cityscouts.repository;

import com.jobs.cityscouts.entity.jobEntity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
