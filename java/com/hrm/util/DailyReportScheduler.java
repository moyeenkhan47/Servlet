package com.hrm.util;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class DailyReportScheduler {

    public void startScheduler() {
        try {
            // Define the job and tie it to your DailyReportJob class
            JobDetail job = JobBuilder.newJob(DailyReportJob.class)
                .withIdentity("dailyReportJob", "group1")
                .build();

            // Define a Cron trigger that runs every day at 8 AM
            Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("dailyReportTrigger", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 16 * * ?"))
                .build();

            // Schedule the job
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        DailyReportScheduler scheduler = new DailyReportScheduler();
        scheduler.startScheduler();
    }
}
