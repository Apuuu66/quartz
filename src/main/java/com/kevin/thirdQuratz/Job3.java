package com.kevin.thirdQuratz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: kevin
 * Date: 2018/6/22
 */
public class Job3 implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //编写具体业务逻辑
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Exec Time Is: " + sf.format(date));
        Trigger trigger = context.getTrigger();
        System.out.println("strat time is :" + sf.format(trigger.getStartTime()));
        System.out.println("end time is : " + sf.format(trigger.getEndTime()));
        JobKey jobKey = trigger.getJobKey();
        System.out.println(jobKey.getName() + "  " + jobKey.getGroup());
    }

}
