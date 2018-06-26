package com.kevin.secondQuartz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: kevin
 * Date: 2018/6/22
 */
public class Job2 implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //编写具体业务逻辑
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Exec Time Is: " + sf.format(date));

        JobKey key = context.getJobDetail().getKey();
        System.out.print(key.getName() + "  ");
        System.out.println(key.getGroup());

        TriggerKey triggerKey = context.getTrigger().getKey();
        System.out.println(triggerKey.getName() + "  " + triggerKey.getGroup());


        JobDataMap jdataMap = context.getJobDetail().getJobDataMap();
        JobDataMap tdataMap = context.getTrigger().getJobDataMap();
        String message = jdataMap.getString("message");
        float floatJobValue = jdataMap.getFloat("FloatJobValue");
        System.out.println(message + "  " + floatJobValue);
        String message1 = tdataMap.getString("message");
        double doubleTriggerValue = tdataMap.getDouble("DoubleTriggerValue");
        System.out.println(message1 + "  " + doubleTriggerValue);

    }
}
