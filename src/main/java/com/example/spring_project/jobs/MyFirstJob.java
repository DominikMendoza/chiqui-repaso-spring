package com.example.spring_project.jobs;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyFirstJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("MyFirstJob is executing...");
        try {
            Thread.sleep(2000);
            log.info("MyFirstJob has completed its task successfully");

        } catch (InterruptedException e) {
            log.error("MyFirstJob was interrupted", e);
            Thread.currentThread().interrupt();
            throw new JobExecutionException("Job interrupted", e);
        }
    }
}
