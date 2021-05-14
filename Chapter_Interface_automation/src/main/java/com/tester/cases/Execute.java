package com.tester.cases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Execute {

    private WeChatRobot robot = new WeChatRobot();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void everyday(String friendName,String timeStr,String message) throws ParseException {
        //获取目标时间
        Date date = getDate(timeStr);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                printLog(friendName,message);
                robot.OpenWeChat();
                robot.ChooseFriends(friendName);
                robot.SendMessage(message);
            }
        };
        timer.schedule(timerTask,date,1000*60*60*24);

    }

    private void printLog(String friendName, String message) {
        System.out.println("-----------------发送消息-----------------");
        System.out.println("当前时间: " + sdf.format(new Date()));
        System.out.println("发送对象: " + friendName);
        System.out.println("发送内容: " + message);
    }

    private Date getDate(String timeStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //获取当前日期 例如 2020-22-22
        String currentDate = sdf.format(new Date());
        //组拼 目标时间 2020-22-22 22:22:22
        String targetTime = currentDate+" "+timeStr;

        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //目标时间 时间戳
        long targetTimer= sdf.parse(targetTime).getTime();
        //当前时间 时间戳
        long currentTimer = new Date().getTime();
        //判断是否已过目标时间
        if(targetTimer < currentTimer ){
            //目标时间加一天
            targetTimer += 1000*60*60*24;
        }
        //返回目标日期
        return new Date(targetTimer);
    }

}

