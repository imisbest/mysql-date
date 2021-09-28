package com.csw.mysqldate;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.csw.mysqldate.dao","com.csw.mysqldate.interceptor"})
public class MysqlDateApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlDateApplication.class, args);
    }

}
