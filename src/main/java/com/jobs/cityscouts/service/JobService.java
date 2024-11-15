package com.jobs.cityscouts.service;

import com.jobs.cityscouts.entity.jobEntity.Job;
import com.jobs.cityscouts.exception.JobNotFoundException;

import java.util.List;

public interface JobService {
    List<Job> findAllJobs();
    void createJob(Job job);

    Job findJobById(Long id) throws JobNotFoundException;

    boolean updateJob(Long id,Job updatedJob);

    boolean deleteJob(Long id);
}
