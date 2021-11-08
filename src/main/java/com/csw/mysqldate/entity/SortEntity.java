package com.csw.mysqldate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class SortEntity implements Comparable<SortEntity> {
    private int id;
    private String ratio;
    private int age;

    @Override
    public int compareTo(SortEntity o) {
        //按照age倒序，id正序排序
        if (this.getAge() > o.getAge()) {
            return -1;
        } else if (this.getAge() < o.getAge()) {
            return 1;
        } else {
            if (this.getAge() > o.getAge()) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
