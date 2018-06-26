package com.kevin.forthQuartz;

import com.kevin.thirdQuratz.Job3;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: kevin
 * Date: 2018/6/25
 */
public class Scheduler5 {
    public static void main(String[] args) throws SchedulerException {
//        获取当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Now Is: " + sf.format(now));
        JobDetail jobDetail = JobBuilder.newJob(Job3.class)
                .withIdentity("myJob3", "group3").build();
        //每秒触发一次任务
        CronTrigger trigger = (CronTrigger)TriggerBuilder.newTrigger()
                .withIdentity("myTrigger5", "group5")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * 14,18 * * ? *"))
                .build();

        //1.2018年内 每天10点15分触发一次
        // 0 15 10 ? * * 2018
        //2.每天的14点整至14点59分55秒，以及18点整至18点59分55秒，每隔5秒触发一次
        // 0/5 * 14,18 * * ? *
        //3.每月周一至周五的10点15分触发一次
        //4.每月最后一天的10点15分触发一次
        //5.每月第三个周五的10点15分触发一次
        //


        //创建Scheduler实例
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);


    }
}