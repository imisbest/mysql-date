/*
package com.csw.mysqldate.controller;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class AutoTianchong {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "E:\\Program Files\\沙箱\\需求3\\工单\\考评测试\\workbook3.xls";
        String[] title = {"物品编号", "物品价格"};
        Workbook workbook = new XSSFWorkbook();

        // 创建需要用户填写的sheet
        XSSFSheet sheet = (XSSFSheet) workbook.createSheet("省市县");
        Row row0 = sheet.createRow(0);
        Row allocationRow = sheet.createRow(0);
        DataValidation validation = getDataValidationByFormula("物品编号", 2, 1);
        File file = new File(filePath);
        FileOutputStream out = new FileOutputStream(file);
        allocationRow.createCell(4).setCellFormula("LOOKUP(A2,hideSheet!A2:A3,hideSheet!B2:B3)");

// 得到验证对象


// 工作表添加验证数据

        sheet.addValidationData(validation);

// 生成输入文件

        setTitle(sheet, title, 0);
            String[] p = {"p1", "p2"};
            String[] price = {"10", "20"};
            int rindex = 0;
            HSSFWorkbook workbook = new HSSFWorkbook();//excel文件对象
            HSSFName name = workbook.createName();
            HSSFSheet sheet = workbook.createSheet("Info");//工作表对象

            HSSFSheet hidesheet = workbook.createSheet("hideSheet");//隐藏一些信息

            HSSFRow row = hidesheet.createRow(rindex++);

//设置物品编号

            for (int i = 0; i

            HSSFCell cell = row.createCell(i);

            cell.setCellValue(p[i]);


// 名称管理


            name.setNameName("物品编号");

            name.setRefersToFormula("hidesheet!$A$" + rindex + ":$" + judgePos(p.size()) + "$" + rindex);

            for (int i = 0; i

            HSSFRow row = hidesheet.createRow(rindex++);

            HSSFCell cell = row.createCell(0);

            cell.setCellValue(p[i]);

            cell = row.createCell(1);

            cell.setCellValue(price[i]);

        }

        workbook.write(out);

        out.close();
    }


//以上大概就是个全过程，主要是设置LOOKUP函数，我原先一直没搞出来是因为函数参数写错了，搞半天没搞出来，今天搞出来了。

//用到的函数我也贴一下，有些用别人的，有些自己写的。

    */
/**
 * 使用已定义的数据源方式设置一个数据验证
 * <p>
 * 返回所在列的字符
 * <p>
 * 创建表头
 *//*


    public static DataValidation getDataValidationByFormula(String formulaString, int startRow, int startCol) {
// 加载下拉列表内容
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(formulaString);
// 设置数据有效性加载在哪个单元格上。

// 四个参数分别是：起始行、终止行、起始列、终止列
        int firstRow = startRow - 1;
        int lastRow = startRow - 1;
        int firstCol = startCol - 1;
        int lastCol = startCol - 1;
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
// 数据有效性对象
        DataValidation validation = new HSSFDataValidation(regions, constraint);
        return validation;

    }

    */
/**
 * 返回所在列的字符
 *//*

    public String judgePos(int size) {
        String[] args = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        boolean tag = true;
        StringBuffer reversepos = new StringBuffer();
        while (tag) {
            int pos = size % 26;
            if (pos == 0) {
                pos = 25;
                size--;
            } else {
                pos -= 1;
            }
            int result = size / 26;
            if (result == 0) {
                reversepos.append(args[pos]);
                tag = false;
            } else {
                reversepos.append(args[pos]);
                size /= 26;
            }
        }
        return reversepos.reverse().toString();
    }

    */
/**
 * 创建表头
 *//*

    public static void setTitle(XSSFSheet sheet, String[] title, int index) {
        HSSFRow row = sheet.createRow(index);
        for (int i = 0; i < title.length; i++) {
            HSSFCell userNameLableCell = row.createCell(i);
            userNameLableCell.setCellValue(title[i]);
        }
    }
}

*/
