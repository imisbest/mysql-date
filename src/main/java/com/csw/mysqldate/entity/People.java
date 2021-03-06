package com.csw.mysqldate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@TableName("people")
public class People {
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;
    @TableField("create_time")
    private Date createTime;
    @TableField("end_time")
    private Date endTime;
}
