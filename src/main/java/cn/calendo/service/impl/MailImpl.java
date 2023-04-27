package cn.calendo.service.impl;

import cn.calendo.service.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MailImpl implements Mail {

    @Autowired
    private JavaMailSender javaMailSender;

    public String from = "youremail@email.com";

    public String to = "youremail@email.com";

    public String cc = "youremail@email.com";

    public String subject = "今日天气";
    
    @Override
    public void mail(ArrayList<String> arrayList) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from + "(你的天气小助手)");
        simpleMailMessage.setTo(to);
        simpleMailMessage.setCc(cc);
        simpleMailMessage.setSubject(subject);
        String string = arrayList.toString();
        String replace = string.replace(",", "");
        String text = replace.substring(1, replace.length() - 1);
        simpleMailMessage.setText(text);
        javaMailSender.send(simpleMailMessage);
    }
}
