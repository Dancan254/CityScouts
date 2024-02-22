package com.jobs.cityscouts.controller.jobControllers;

import com.jobs.cityscouts.entity.jobEntity.Job;
import com.jobs.cityscouts.exception.JobNotFoundException;
import com.jobs.cityscouts.service.JobServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @Autowired
    JobServiceImpl jobService;
    //LIST JOBS
    @GetMapping("/All")
    public ResponseEntity<List<Job>> findAllJobs(){
        List<Job> foundJobs = jobService.findAllJobs();
        return new ResponseEntity<>(foundJobs, HttpStatus.FOUND);
    }
    //GET JOB BY ID
    @GetMapping("/job/{id}")
    public ResponseEntity<Job> findJobById(@PathVariable Long id) {
        try {
            Job job = jobService.findJobById(id);
            return ResponseEntity.ok(job);
        } catch (JobNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //create jobs
    @PostMapping("/job/createJob")
    public ResponseEntity<String> createJob(@RequestBody Job job){
        jobService.createJob(job);
        String message = "Job created successfully";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    //update job
    @PutMapping("/job/update/{id}")
    public ResponseEntity<String> updateJob(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean isUpdated = jobService.updateJob(id,updatedJob);
        if (isUpdated){
            return new ResponseEntity<>("Job successfully updated", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Failed to update, Ensure you have passed the right id",HttpStatus.NOT_FOUND);
        }
    }

    //delete a job
    @DeleteMapping("/job/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable Long id){
        boolean isDeleted = jobService.deleteJob(id);
        if(isDeleted){
            return new ResponseEntity<>("Job successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to delete",HttpStatus.NOT_FOUND);
    }
}
