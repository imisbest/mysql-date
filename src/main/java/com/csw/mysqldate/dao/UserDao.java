package com.csw.mysqldate.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csw.mysqldate.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {
    List<User> selectBY();

    void updateCommit();


    List<User> selectBe();

    User selectUserById(@Param("uid") int uid);
}
