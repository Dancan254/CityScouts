package com.jobs.cityscouts.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.jobs.cityscouts.entity.jobEntity.Job;
import com.jobs.cityscouts.exception.JobNotFoundException;
import com.jobs.cityscouts.repository.JobRepository;

@Service
public class JobServiceImpl implements JobService{

    @Autowired
    JobRepository jobRepository;

    @Override
    public List<Job> findAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        job.setCreatedAt(LocalDateTime.now());
        jobRepository.save(job);
    }

    @Override
    public Job findJobById(Long id) throws JobNotFoundException {
    return jobRepository.findById(id)
            .orElseThrow(() -> new JobNotFoundException("Job with ID " + id + " not found"));
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> optionalJob = jobRepository.findById(id);
        try {
            //check if the id matches
            if (optionalJob.isPresent()) {
                //set new values
                Job job = optionalJob.get();
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setLocation(updatedJob.getLocation());
                updatedJob.setEditedAt(LocalDateTime.now());

                jobRepository.save(updatedJob);
                return true;

            }
        }catch (DataAccessException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteJob(Long id) {
        try {
            if (jobRepository.existsById(id)) { // Check if the job exists before attempting to delete
                jobRepository.deleteById(id); // Directly delete the job by ID

                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
