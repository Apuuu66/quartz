package com.kevin.secondQuartz;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: kevin
 * Date: 2018/6/22
 */
public class Job2_1 implements Job {
    private String message;
    private float FloatJobValue;
    private double DoubleTriggerValue;

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

        System.out.println(message);
        System.out.println(FloatJobValue);
        System.out.println(DoubleTriggerValue);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public float getFloatJobValue() {
        return FloatJobValue;
    }

    public void setFloatJobValue(float floatJobValue) {
        FloatJobValue = floatJobValue;
    }

    public double getDoubleTriggerValue() {
        return DoubleTriggerValue;
    }

    public void setDoubleTriggerValue(double doubleTriggerValue) {
        DoubleTriggerValue = doubleTriggerValue;
    }
}
