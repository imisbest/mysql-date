package com.csw.mysqldate.controller;

import com.csw.mysqldate.dao.PeopleDao;
import com.csw.mysqldate.dao.UserDao;
import com.csw.mysqldate.entity.People;
import com.csw.mysqldate.entity.User;
import com.csw.mysqldate.vo.RequestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

import static java.lang.Thread.sleep;

@RestController
@RequestMapping("aa")
public class UserController {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PeopleDao peopleDao;

    @RequestMapping("insert")
    public void user() throws InterruptedException {
        User user = new User();
        user.setCreateTime(new Date());
        sleep(1000);
        //user.setEndTime(new Date());
        userDao.insert(user);
    }

    @RequestMapping("insertP")
    public void people() throws InterruptedException {
        People people = new People();
        people.setCreateTime(new Date());
        sleep(1000);
        people.setEndTime(new Date());
        peopleDao.insert(people);
    }

    //get请求方式不推荐
    @RequestMapping("dx")
    public List<User> userTest(String num) {/*比较当前时间和数据库时间大小*/
        System.out.println("num]"+num);
        userDao.selectBe();
        return userDao.selectBY();
    }

    //不推荐
    @PostMapping("dx2")
    public List<User> userTest2(@RequestBody String num) {/*比较当前时间和数据库时间大小*/
        System.out.println("num]"+num);
        userDao.selectBe();
        return userDao.selectBY();
    }

    //postmapping请求推荐格式
    @PostMapping("dx3")
    public List<User> userTest3(@RequestBody RequestVO requestVO) {/*比较当前时间和数据库时间大小*/
        String num = requestVO.getNum();
        System.out.println("num]" + num);
        userDao.selectBe();
        return userDao.selectBY();
    }
    @RequestMapping("pg")
    public void update() {/*拿时间作比较，批量更新数据库*/
        userDao.updateCommit();
    }

    @RequestMapping("ccc")
    public List<User> selectBe() {/*查找某个时间开始前几年，几天，几个小时*/


        System.out.println("aaa");
        return userDao.selectBe();
    }

}
