package com.practise.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.concurrent.CompletableFuture;

public class MyJob implements Job {
    static int countTask = 0;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        CompletableFuture.runAsync(() -> {
            try {
                SampleTask();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

    }

    public Runnable SampleTask() throws InterruptedException {
        int taskNum = countTask++;
        System.out.println("Starting ur task "+taskNum+", when u will start");
        Thread.sleep(20000);
        System.out.println("Ur task is Done: task-"+taskNum);
        return null;
    }
}
