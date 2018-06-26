package com.kevin.thirdQuratz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: kevin
 * Date: 2018/6/25
 */
public class Scheduler3 {
    public static void main(String[] args) throws SchedulerException {
//        获取当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Now Is: " + sf.format(now));
//        使用jobDataMap
        JobDetail jobDetail = JobBuilder.newJob(Job3.class)
                .withIdentity("myJob3", "group3").build();
//        获取距离当前时间3秒后
        Date begin = new Date(now.getTime() + 3000);
        Date end = new Date(now.getTime() + 6000);
        //创建一个Trigger实例，定义该Job立即执行，并且每隔两秒钟重复执行一次
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger3", "group3")
                .startAt(now)
                .endAt(end)
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(2).repeatForever()
                ).build();
        //创建Scheduler实例
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);


    }
}