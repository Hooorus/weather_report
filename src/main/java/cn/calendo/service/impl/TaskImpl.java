package cn.calendo.service.impl;

import cn.calendo.service.Mail;
import cn.calendo.service.Task;
import cn.calendo.service.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class TaskImpl implements Task {

    @Autowired
    public Mail mail;

    @Autowired
    public Weather weather;

    @Override
//    @Scheduled(cron = "0 40 6 * * *")
    @Scheduled(cron = "0 40 6 * * *")
//    @Scheduled(cron = "* * * * * *")
    public void task() {
        Date date = new Date();
        String time = date.toString();
        System.out.println(time + "  prepare to get weather...");
        ArrayList<String> weather = this.weather.weather();
        System.out.println(time + "  weather get...");
        System.out.println(time + "  prepare to send...");
        mail.mail(weather);
        System.out.println(time + "  email send...");
    }
}
