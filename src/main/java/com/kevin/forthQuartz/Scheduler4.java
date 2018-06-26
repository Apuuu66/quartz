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
public class Scheduler4 {
    public static void main(String[] args) throws SchedulerException {
//        获取当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Now Is: " + sf.format(now));
        JobDetail jobDetail = JobBuilder.newJob(Job3.class)
                .withIdentity("myJob3", "group3").build();
//        获取距离当前时间4秒后
        Date begin = new Date(now.getTime() + 4000);
        Date end = new Date(begin.getTime() + 6000);

        //eg1:创建一个Trigger实例，四秒之后首次执行且只执行一次
/*        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity("myTrigger4", "group4")
                .startAt(begin)
                .build();*/

        //eg2:创建一个Trigger实例，四秒之后首次执行,之后每隔两秒执行一次
/*        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity("myTrigger4", "group4")
                .startAt(begin)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(2))
                .build();*/

        //eg2:创建一个Trigger实例，四秒之后首次执行,之后每隔两秒执行一次,直到距离开始6秒之后结束
        SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger()
                .withIdentity("myTrigger4", "group4")
                .startAt(begin).endAt(end)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).withRepeatCount(3))
                .build();
        //创建Scheduler实例
        SchedulerFactory sfact = new StdSchedulerFactory();
        Scheduler scheduler = sfact.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);


    }
}