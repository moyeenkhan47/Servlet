package com.hrm.util;

import com.hrm.util.EmailUtil;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ReportScheduler {

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void start() {
        Runnable task = new Runnable() {
            public void run() {
                try {
                    // Replace with actual recipient email address
                    EmailUtil.sendPdfReport("moyeenkhan47@gmail.com");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        // Schedule the task to run every day at 10 PM
        scheduler.scheduleAtFixedRate(task, getInitialDelay(), 24, TimeUnit.HOURS);
    }

    private static long getInitialDelay() {
        // Get current time
        Calendar now = Calendar.getInstance();
        // Define the target time (10 PM)
        Calendar targetTime = Calendar.getInstance();
        targetTime.set(Calendar.HOUR_OF_DAY, 21); // 10 PM
        targetTime.set(Calendar.MINUTE, 50);
        targetTime.set(Calendar.SECOND, 0);
        targetTime.set(Calendar.MILLISECOND, 0);

        // If the target time is before the current time, set it for the next day
        if (now.after(targetTime)) {
            targetTime.add(Calendar.DAY_OF_MONTH, 1);
        }

        // Calculate the delay in milliseconds
        long initialDelay = targetTime.getTimeInMillis() - now.getTimeInMillis();
        return initialDelay;
    }
    public static void stop() {
        scheduler.shutdown();
    }
}
