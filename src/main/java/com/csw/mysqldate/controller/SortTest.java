package com.csw.mysqldate.controller;

import com.csw.mysqldate.entity.SortEntity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class SortTest {
    public static void main(String[] args) {
        List<SortEntity> sortEntityList = new ArrayList<>();

        SortEntity sortEntity1 = new SortEntity();
        sortEntity1.setId(1);
        sortEntity1.setRatio("34.23");
        sortEntity1.setAge(1);
        sortEntityList.add(sortEntity1);

        SortEntity sortEntity2 = new SortEntity();
        sortEntity2.setId(2);
        sortEntity2.setRatio("33.23");
        sortEntity2.setAge(3);
        sortEntityList.add(sortEntity2);

        SortEntity sortEntity3 = new SortEntity();
        sortEntity3.setId(3);
        sortEntity3.setRatio("33.23");
        sortEntity3.setAge(2);
        sortEntityList.add(sortEntity3);

        SortEntity sortEntity4 = new SortEntity();
        sortEntity4.setId(4);
        sortEntity4.setRatio("35.23");
        sortEntity4.setAge(3);
        sortEntityList.add(sortEntity4);

        SortEntity sortEntity5 = new SortEntity();
        sortEntity5.setId(5);
        sortEntity5.setRatio("36.23");
        sortEntity5.setAge(5);
        sortEntityList.add(sortEntity5);
        System.out.println("//原始//////////////////排序前");
        for (SortEntity sortEntity : sortEntityList) {
            System.out.println(sortEntity);
        }

        sortEntityList.sort(new Comparator() {
            public int compare(Object o1, Object o2) {
                SortEntity stu1 = (SortEntity) o1;
                SortEntity stu2 = (SortEntity) o2;
                if (!stu2.getRatio().equals(stu1.getRatio())) {
                    return stu2.getRatio().compareTo(stu1.getRatio());
                } else {
                    return (stu2.getId() + "").compareTo(stu1.getId() + "");
                }
            }
        });

        System.out.println("//String类型排序////////////////////排序后");
        for (SortEntity sortEntity : sortEntityList) {
            System.out.println(sortEntity);
        }
        sortEntityList.sort(SortEntity::compareTo);
        System.out.println("//实体int类型排序////////////////////排序后");
        for (SortEntity sortEntity : sortEntityList) {
            System.out.println(sortEntity);
        }

    }


}
