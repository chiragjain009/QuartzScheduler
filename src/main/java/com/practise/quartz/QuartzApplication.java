package com.practise.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuartzApplication {

	public static void main(String[] args) throws SchedulerException {
		SpringApplication.run(QuartzApplication.class, args);

		//create a scheduler Factory
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();

		//get Scheduler from Factory
		Scheduler scheduler = schedulerFactory.getScheduler();

		// Define a Job and tie it to our job class
		JobDetail job = JobBuilder.newJob(MyJob.class)
				.withIdentity("myjob","grp1").build();

		// Create a Trigger that fires every 10 seconds
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("mytrigger","group1")
				.startNow()
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
				.build();


		// Tell the Scheduler to schedule the Job using our Trigger
		scheduler.scheduleJob(job,trigger);

		//start
		scheduler.start();


	}

}
