package com.kevin.secondQuartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: kevin
 * Date: 2018/6/25
 */
public class Scheduler2 {
    public static void main(String[] args) throws SchedulerException {
//        使用jobDataMap
        JobDetail jobDetail = JobBuilder.newJob(Job2.class).withIdentity("myJob2", "group2")
                .usingJobData("message", "hello myjob2")
                .usingJobData("FloatJobValue", 3.14F).build();
        JobDetail jobDetai2_1 = JobBuilder.newJob(Job2_1.class).withIdentity("myJob2", "group2")
                .usingJobData("message", "hello myjob2")
                .usingJobData("FloatJobValue", 3.14F).build();
        //获取jobDetail属性
        System.out.println(jobDetail.getKey().getName());
        System.out.println(jobDetail.getKey().getGroup());
        System.out.println(jobDetail.getJobClass());

        //创建一个Trigger实例，定义该Job立即执行，并且每隔两秒钟重复执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger2", "group2")
                .usingJobData("message", "hello myTrigger2")
                .usingJobData("DoubleTriggerValue", 2.0D)
                .startNow()
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(2).repeatForever()
                ).build();
        //创建Scheduler实例
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.start();
//        scheduler.scheduleJob(jobDetail, trigger);
        scheduler.scheduleJob(jobDetai2_1, trigger);

        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Now Is: " + sf.format(date));
    }
}