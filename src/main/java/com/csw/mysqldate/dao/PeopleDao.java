package com.csw.mysqldate.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.csw.mysqldate.entity.People;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PeopleDao extends BaseMapper<People> {
}
