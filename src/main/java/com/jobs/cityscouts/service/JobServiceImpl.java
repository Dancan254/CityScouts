package com.jobs.cityscouts.service;

import com.jobs.cityscouts.entity.jobEntity.Job;
import com.jobs.cityscouts.exception.JobNotFoundException;
import com.jobs.cityscouts.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{

    @Autowired
    JobRepository jobRepository;
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
        List<Job> jobs = findAllJobs();
        //iterate
        for(Job job: jobs){
            //check if the id matches
            if (job.getId().equals(id)){
                //set new values
                job.setTitle(updatedJob.getTitle());
                job.setDescription(updatedJob.getDescription());
                job.setMaxSalary(updatedJob.getMaxSalary());
                job.setMinSalary(updatedJob.getMinSalary());
                job.setLocation(updatedJob.getLocation());
                updatedJob.setEditedAt(LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteJob(Long id) {
        List<Job> jobs = findAllJobs();
        //check if it exists
        Iterator<Job> iterator = jobs.iterator();
        while(iterator.hasNext()){
            Job job = iterator.next();
            //check if id matches
            if (job.getId().equals(id)){
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
