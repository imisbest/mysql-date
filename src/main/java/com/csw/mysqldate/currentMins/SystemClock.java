package com.csw.mysqldate.currentMins;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 时钟工具
 **/
class SystemClock {

    /**
     * 设置周期
     */
    private final long period;

    /**
     * 用来作为我们时间戳存储的容器
     */
    private final AtomicLong now;

    private SystemClock(long period) {
        this.period = period;
        this.now = new AtomicLong(System.currentTimeMillis());
        scheduleClockUpdating();
    }

    /**
     * 初始化单例
     * @return
     */
    private static SystemClock instance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * 获取毫秒时间戳 替换System.currentTimeMillis()
     * @return
     */
    public static long currentTimeMillis() {
        return instance().now();
    }

    private long now() {
        return now.get();
    }

    /**
     * 初始化定时器
     */
    private void scheduleClockUpdating() {
        ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(1, r -> {
            Thread thread = new Thread(r, "System Clock");
            //设置为守护线程
            thread.setDaemon(true);
            return thread;
        });
        scheduler.scheduleAtFixedRate(() -> now.set(System.currentTimeMillis()), period, period, TimeUnit.MILLISECONDS);
    }

    private static class InstanceHolder {
        //设置1ms更新一次时间
        static final SystemClock INSTANCE = new SystemClock(1);
    }
}
/*————————————————
        版权声明：本文为CSDN博主「程冯冯」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
        原文链接：https://blog.csdn.net/qq_30062181/article/details/108681101*/
