package com.example.spring_project.config;

import com.example.spring_project.jobs.MyFirstJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    private static final String JOB_GROUP_NAME = "mainGroup";
    @Bean
    public JobDetail miJobDetail() {
        return JobBuilder.newJob(MyFirstJob.class)
                .withIdentity("miTask", JOB_GROUP_NAME)
                .withDescription("My first Quartz job")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger miTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(miJobDetail())
                .withIdentity("miTrigger", JOB_GROUP_NAME)
                .withDescription("Execute every 5 minutes")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/5 * * * ?"))
                .build();
    }

    @Bean
    public Trigger miTriggerSimple() {
        return TriggerBuilder.newTrigger()
                .forJob(miJobDetail())
                .withIdentity("miSimpleTrigge", JOB_GROUP_NAME)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();
    }
}