package com.csw.mysqldate.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFDataValidation;
import org.apache.poi.xssf.usermodel.XSSFDataValidationConstraint;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

public class TwoExportSuper {

    public static void main(String[] args) {
        Cascade();
    }

    public static void Cascade() {
        String filePath = "E:\\Program Files\\沙箱\\需求3\\工单\\考评测试\\workbook.xls";
        int maxLine = 9999;
        // 创建一个excel
        @SuppressWarnings("resource")
        Workbook workbook = new XSSFWorkbook();

        // 创建需要用户填写的sheet
        XSSFSheet sheetZS = (XSSFSheet) workbook.createSheet("省市县");
        Row rowZS = sheetZS.createRow(0);
        String[] titleZS = {"新下拉", "老下拉", "跟老动", "跟老展示", "跟新展示"};
        Cell cellZS;
        for (int i = 0; i < titleZS.length; i++) {
            cellZS = rowZS.createCell(i + 4);
            cellZS.setCellValue(titleZS[i]);
        }
        for (int i = 1; i < maxLine; i++) {
            sheetZS.createRow(i);
        }
        //得到第一级省名称，放在列表里
        String[] firstXLK = {"字母", "数字"};
        //依次列出各省的市、各市的县
        String[] ziMu = {"abcdefg", "hijklmn", "opq", "rst", "uvwxyz"};
        String[] shuZi = {"12345", "金木水火土"};
        //将有子区域的父区域放到一个数组中
        String[] areaFatherNameArr = {"字母", "数字"};
        Map<String, String[]> areaMap = new HashMap<>();
        areaMap.put("字母", ziMu);
        areaMap.put("数字", shuZi);

        //创建一个专门用来存放地区信息的隐藏sheet页
        //因此也不能在现实页之前创建，否则无法隐藏。
        String areaSheetName = "area";
        Sheet areaSheet = workbook.createSheet(areaSheetName);
        //这一行作用是将此sheet隐藏，功能未完成时注释此行,可以查看隐藏sheet中信息是否正确
        // workbook.setSheetHidden(workbook.getSheetIndex(areaSheetName), true);

        int rowId = 0;
        // 设置第一行，存省的信息
        Row provinceRow = areaSheet.createRow(rowId++);
        provinceRow.createCell(0).setCellValue("渠道");
        for (int i = 0; i < firstXLK.length; i++) {//firstLevel
            Cell row2 = provinceRow.createCell(i + 1);
            row2.setCellValue(firstXLK[i]);//firstLevel
        }
        // 将具体的数据写入到每一行中，行开头为父级区域，后面是子区域。
        for (String key : areaFatherNameArr) {
            String[] son = areaMap.get(key);
            Row row1 = areaSheet.createRow(rowId++);
            row1.createCell(0).setCellValue(key);
            for (int j = 0; j < son.length; j++) {
                Cell cell0 = row1.createCell(j + 1);
                cell0.setCellValue(son[j]);
            }

            // 添加名称管理器,rowId从1开始
            String range = getRange(1, rowId, son.length);
            Name name = workbook.createName();
            //key不可重复
            String fName = key;
            String nName = fName.replace("（", "_").replace("(", "_");
            String lName = nName.replace("）", "_").replace(")", "_");
            name.setNameName(lName);
            String formula = areaSheetName + "!" + range;
            name.setRefersToFormula(formula);
        }
        /**
         * 放入主表并设置有效性
         */
        setDropdownToExcel(sheetZS, firstXLK, 1, maxLine, 5, 5);
        initFgsIndustrydata(workbook, sheetZS, firstXLK, 1, maxLine, 4, 4, areaSheetName);
        /**
         *拼接同步判断表达式
         */
        for (int i = 1; i < maxLine; i++) {
            Row row7 = sheetZS.getRow(i);
            int j = i + 1;
            String lineFF = "F";
            String sStart = "IF(" + lineFF + j + "=\"\",\"\"";
            for (String aa : areaFatherNameArr) {
                String fName = aa;
                String nName = fName.replace("（", "_").replace("(", "_");
                String n = nName.replace("）", "_").replace(")", "_");
                sStart += ",IF(" + lineFF + j + "=\"" + n + "\",VLOOKUP(" + lineFF + j + "," + n + ",1)";
            }
            sStart += ",\"不知道\"";
            for (int m = 0; m < areaFatherNameArr.length + 1; m++) {
                sStart += ")";
            }
            //String length = "IF(F4=\"\",\"\",IF(F4=\"数字\",VLOOKUP(F4,数字,2),IF(F4=\"字母\",VLOOKUP(F4,字母,1),\"不知道\")))";
            row7.createCell(7).setCellFormula(sStart);
        }
        /**
         *拼接同步判断表达式
         */
        for (int i = 1; i < maxLine; i++) {
            Row row7 = sheetZS.getRow(i);
            int j = i + 1;
            String lineFF = "E";
            String sStart = "IF(" + lineFF + j + "=\"\",\"\"";
            for (String aa : areaFatherNameArr) {
                String fName = aa;
                String nName = fName.replace("（", "_").replace("(", "_");
                String n = nName.replace("）", "_").replace(")", "_");
                sStart += ",IF(" + lineFF + j + "=\"" + n + "\",VLOOKUP(" + lineFF + j + "," + n + ",2)";
            }
            sStart += ",\"不知道\"";
            for (int m = 0; m < areaFatherNameArr.length + 1; m++) {
                sStart += ")";
            }
            //String length = "IF(F4=\"\",\"\",IF(F4=\"数字\",VLOOKUP(F4,数字,2),IF(F4=\"字母\",VLOOKUP(F4,字母,1),\"不知道\")))";
            row7.createCell(8).setCellFormula(sStart);
        }

        //二级联动设置有效性
        for (int i = 2; i < maxLine; i++) {
            setDataValidation("F", sheetZS, i, 7);
        }

        FileOutputStream os = null;
        try {
            os = new FileOutputStream(filePath);
            workbook.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(os);
        }
    }

    /**
     * 破除255字符限制
     *
     * @param wk
     * @param sheet
     * @param nameList
     * @param firstRow
     * @param lastRow
     * @param firstCol
     * @param lastCol
     * @param sheetName
     */
    private static void initFgsIndustrydata(Workbook wk, Sheet sheet, String[] nameList, int firstRow, int lastRow, int firstCol, int lastCol, String sheetName) {
        Name namedCell = wk.createName();
        String nameName = sheetName + "xlk";
        namedCell.setNameName(nameName);
        //引用列，industry为上面创建一张隐藏掉sheet表,$A$2:$A$n,是此表中存放掉数据
        String refersToFormula = sheetName + "!$A$2:$A$" + (nameList.length + 1);
        namedCell.setRefersToFormula(refersToFormula);
        // 四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        DataValidationHelper dataValidationHelper = sheet.getDataValidationHelper();
        //验证约束
        DataValidationConstraint dataValidationConstraint = dataValidationHelper.createFormulaListConstraint(nameName);//firstLevel
        //增加数据校验
        DataValidation provinceDataValidation = dataValidationHelper.createValidation(dataValidationConstraint, regions);
        //处理Excel兼容性问题
        if (provinceDataValidation instanceof XSSFDataValidation) {
            provinceDataValidation.setSuppressDropDownArrow(true);
            provinceDataValidation.setShowErrorBox(true);
        } else {
            provinceDataValidation.setSuppressDropDownArrow(false);
        }
        sheet.addValidationData(provinceDataValidation);
    }


    /**
     * 设置有效性
     *
     * @param offset 主影响单元格所在列，即此单元格由哪个单元格影响联动
     * @param sheet
     * @param rowNum 行数
     * @param colNum 列数
     */
    public static void setDataValidation(String offset, XSSFSheet sheet, int rowNum, int colNum) {
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        DataValidation data_validation_list;
        data_validation_list = getDataValidationByFormula(
                "INDIRECT($" + offset + (rowNum) + ")", rowNum, colNum, dvHelper);
        sheet.addValidationData(data_validation_list);
    }

    /**
     * 有255字符限制
     *
     * @param sheet
     * @param strs     下拉框中的值
     * @param firstRow 下标
     * @param lastRow
     * @param firstCol
     * @param lastCol
     * @date 2020年1月6日 下午3:13:50
     * @author ***
     */
    private static void setDropdownToExcel(Sheet sheet, String[] strs, int firstRow, int lastRow, int firstCol, int lastCol) {
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, lastRow, firstCol, lastCol);
        DataValidationHelper dataValidationHelper = sheet.getDataValidationHelper();
        DataValidationConstraint createExplicitListConstraint = dataValidationHelper.createExplicitListConstraint(strs);
        DataValidation createValidation = dataValidationHelper.createValidation(createExplicitListConstraint, regions);
        //处理Excel兼容性问题
        if (createValidation instanceof XSSFDataValidation) {
            createValidation.setSuppressDropDownArrow(true);
            createValidation.setShowErrorBox(true);
        } else {
            createValidation.setSuppressDropDownArrow(false);
        }
        sheet.addValidationData(createValidation);
    }

    /**
     * 加载下拉列表内容
     *
     * @param formulaString
     * @param naturalRowIndex
     * @param naturalColumnIndex
     * @param dvHelper
     * @return
     */
    private static DataValidation getDataValidationByFormula(
            String formulaString, int naturalRowIndex, int naturalColumnIndex, XSSFDataValidationHelper dvHelper) {
        // 加载下拉列表内容
        // 举例：若formulaString = "INDIRECT($A$2)" 表示规则数据会从名称管理器中获取key与单元格 A2 值相同的数据，
        //如果A2是江苏省，那么此处就是江苏省下的市信息。
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createFormulaListConstraint(formulaString);
        // 设置数据有效性加载在哪个单元格上。
        // 四个参数分别是：起始行、终止行、起始列、终止列
        int firstRow = naturalRowIndex - 1;
        int lastRow = naturalRowIndex - 1;
        int firstCol = naturalColumnIndex - 1;
        int lastCol = naturalColumnIndex - 1;
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,
                lastRow, firstCol, lastCol);
        // 数据有效性对象
        // 绑定
        XSSFDataValidation data_validation_list = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, regions);
        data_validation_list.setEmptyCellAllowed(false);
        if (data_validation_list instanceof XSSFDataValidation) {
            data_validation_list.setSuppressDropDownArrow(true);
            data_validation_list.setShowErrorBox(true);
        } else {
            data_validation_list.setSuppressDropDownArrow(false);
        }
        // 设置输入信息提示信息
        data_validation_list.createPromptBox("下拉选择提示", "请使用下拉方式选择合适的值！");
        // 设置输入错误提示信息
        //data_validation_list.createErrorBox("选择错误提示", "你输入的值未在备选列表中，请下拉选择合适的值！");
        return data_validation_list;
    }

    /**
     * 计算formula
     *
     * @param offset   偏移量，如果给0，表示从A列开始，1，就是从B列
     * @param rowId    第几行
     * @param colCount 一共多少列
     * @return 如果给入参 1,1,10. 表示从B1-K1。最终返回 $B$1:$K$1
     */
    public static String getRange(int offset, int rowId, int colCount) {
        char start = (char) ('A' + offset);
        if (colCount <= 25) {
            char end = (char) (start + colCount - 1);
            return "$" + start + "$" + rowId + ":$" + end + "$" + rowId;
        } else {
            char endPrefix = 'A';
            char endSuffix = 'A';
            if ((colCount - 25) / 26 == 0 || colCount == 51) {// 26-51之间，包括边界（仅两次字母表计算）
                if ((colCount - 25) % 26 == 0) {// 边界值
                    endSuffix = (char) ('A' + 25);
                } else {
                    endSuffix = (char) ('A' + (colCount - 25) % 26 - 1);
                }
            } else {// 51以上
                if ((colCount - 25) % 26 == 0) {
                    endSuffix = (char) ('A' + 25);
                    endPrefix = (char) (endPrefix + (colCount - 25) / 26 - 1);
                } else {
                    endSuffix = (char) ('A' + (colCount - 25) % 26 - 1);
                    endPrefix = (char) (endPrefix + (colCount - 25) / 26);
                }
            }
            return "$" + start + "$" + rowId + ":$" + endPrefix + endSuffix + "$" + rowId;
        }
    }
}
