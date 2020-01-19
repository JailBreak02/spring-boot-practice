package com.example.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Scheduler2Task {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 6 * 1000)
    public void reportCurrentTime() {

        // @Scheduled(fixedRate = 6000) ：上一次开始执行时间点之后6秒再执行
        // @Scheduled(fixedDelay = 6000) ：上一次执行完毕时间点之后6秒再执行
        // @Scheduled(initialDelay=1000, fixedRate=6000) ：第一次延迟1秒后执行，之后按 fixedRate 的规则每6秒执行一次

        System.out.println("现在时间: " + dateFormat.format(new Date()));
    }

}
