package com.csw.mysqldate.currentMins;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SystemClockTest {
    private static final Logger logger = LoggerFactory.getLogger(SystemCurrentMins.class);

    public static void main(String[] args) {
        for (int j = 0; j < 10; j++) {
            long start = System.currentTimeMillis();
            for (int i = 0; i < 10000000; i++) {
                SystemClock.currentTimeMillis();
            }
            long end = System.currentTimeMillis();
            long result = end - start;
            logger.info("执行一千万次的执行时间为{}", result);
        }
    }
}
