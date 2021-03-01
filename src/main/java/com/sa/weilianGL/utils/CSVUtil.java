package com.sa.weilianGL.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

@Slf4j
public class CSVUtil {


        public static File createMaCSVFile(HashMap map, String outPutPath, String fileName) {

            String activateHeader = "日期,当日激活数,当日付费数, 当日付费金额 , 当日APRU , 当日ltv ,1日付费数, 1日付费金额 , 1日APRU , 1日ltv ,3日付费数, 3日付费金额 , 3日APRU , 3日ltv ,5日付费数, 5日付费金额 , 5日APRU , 5日ltv ,7日付费数, 7日付费金额 , 7日APRU , 7日ltv ,14日付费数, 14日付费金额 , 14日APRU , 14日ltv ,30日付费数, 30日付费金额 , 30日APRU , 30日APRU ";

//            HashMap<String, String> map = new HashMap<>();
//            map.put("key",value);
            return createCSVFile(map,outPutPath,fileName,activateHeader);
        }

        public static File createMrCSVFile(HashMap map, String outPutPath, String fileName) {

            String activateHeader = "日期,当日激活数,当日付费数, 当日付费金额 , 当日APRU , 当日ltv ,1日付费数, 1日付费金额 , 1日APRU , 1日ltv ,3日付费数, 3日付费金额 , 3日APRU , 3日ltv ,5日付费数, 5日付费金额 , 5日APRU , 5日ltv ,7日付费数, 7日付费金额 , 7日APRU , 7日ltv ,14日付费数, 14日付费金额 , 14日APRU , 14日ltv ,30日付费数, 30日付费金额 , 30日APRU , 30日APRU ";
            String registerHeader = activateHeader.replaceAll("当日激活数","新注册用户数");

//            HashMap<String, String> map = new HashMap<>();
//            map.put("key",value);
            return createCSVFile(map,outPutPath,fileName,registerHeader);
        }


        public static File createMbCSVFile(HashMap map, String outPutPath, String fileName) {

            String activateHeader = "日期,当日激活数,当日付费数, 当日付费金额 , 当日APRU , 当日ltv ,1日付费数, 1日付费金额 , 1日APRU , 1日ltv ,3日付费数, 3日付费金额 , 3日APRU , 3日ltv ,5日付费数, 5日付费金额 , 5日APRU , 5日ltv ,7日付费数, 7日付费金额 , 7日APRU , 7日ltv ,14日付费数, 14日付费金额 , 14日APRU , 14日ltv ,30日付费数, 30日付费金额 , 30日APRU , 30日APRU ";
            String backflowHeader = activateHeader.replaceAll("当日激活数","回流用户数");

//            HashMap<String, String> map = new HashMap<>();
//            map.put("key",value);
            return createCSVFile(map,outPutPath,fileName,backflowHeader);
        }


    public static File createaCSVFile(HashMap<String, String> map, String outPutPath, String fileName) {

            String activateHeader = "日期,广告系列来源,广告系列名称,广告系列内容,当日激活数,当日付费数, 当日付费金额 , 当日APRU , 当日ltv ,1日付费数, 1日付费金额 , 1日APRU , 1日ltv ,3日付费数, 3日付费金额 , 3日APRU , 3日ltv ,5日付费数, 5日付费金额 , 5日APRU , 5日ltv ,7日付费数, 7日付费金额 , 7日APRU , 7日ltv ,14日付费数, 14日付费金额 , 14日APRU , 14日ltv ,30日付费数, 30日付费金额 , 30日APRU , 30日APRU ";
            return createCSVFile(map,outPutPath,fileName,activateHeader);
        }

        public static File createrCSVFile(HashMap<String, String> map, String outPutPath, String fileName) {

            String activateHeader = "日期,广告系列来源,广告系列名称,广告系列内容,当日激活数,当日付费数, 当日付费金额 , 当日APRU , 当日ltv ,1日付费数, 1日付费金额 , 1日APRU , 1日ltv ,3日付费数, 3日付费金额 , 3日APRU , 3日ltv ,5日付费数, 5日付费金额 , 5日APRU , 5日ltv ,7日付费数, 7日付费金额 , 7日APRU , 7日ltv ,14日付费数, 14日付费金额 , 14日APRU , 14日ltv ,30日付费数, 30日付费金额 , 30日APRU , 30日APRU ";
            String registerHeader = activateHeader.replaceAll("当日激活数","新注册用户数");
            return createCSVFile(map,outPutPath,fileName,registerHeader);
        }

        public static File createbCSVFile(HashMap<String, String> map, String outPutPath, String fileName) {

            String activateHeader = "日期,广告系列来源,广告系列名称,广告系列内容,当日激活数,当日付费数, 当日付费金额 , 当日APRU , 当日ltv ,1日付费数, 1日付费金额 , 1日APRU , 1日ltv ,3日付费数, 3日付费金额 , 3日APRU , 3日ltv ,5日付费数, 5日付费金额 , 5日APRU , 5日ltv ,7日付费数, 7日付费金额 , 7日APRU , 7日ltv ,14日付费数, 14日付费金额 , 14日APRU , 14日ltv ,30日付费数, 30日付费金额 , 30日APRU , 30日APRU ";
            String backflowHeader = activateHeader.replaceAll("当日激活数","回流用户数");
            return createCSVFile(map,outPutPath,fileName,backflowHeader);
        }


        public static File createCSVFile(HashMap map, String outPutPath, String fileName,String HeaderString) {


            File csvFile = null;
            BufferedWriter csvFileOutputStream = null;
            try {
                File file = new File(outPutPath);
                if (!file.exists()) {
                    file.mkdir();
                }
                //定义文件名格式并创建
                csvFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
                //成功
                if(csvFile.exists())
                {
                    //改名
                    csvFile.renameTo(new File(outPutPath+"/"+fileName+".csv"));
                    System.out.println(csvFile.getPath());
                }

                log.info("csvFile：" + csvFile+ " 开始写入！");
                // UTF-8使正确读取分隔符","
                //如果生产文件乱码，windows下用gbk，linux用UTF-8
                csvFileOutputStream = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPutPath+"/"+fileName+".csv"), "UTF-8"), 1024);
//                        csvFile), "UTF-8"), 1024);
//                System.out.println("csvFileOutputStream：" + csvFileOutputStream);
                // 写入文件头部
                csvFileOutputStream.write(HeaderString);

//            for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
//                java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
//                csvFileOutputStream.write((String) propertyEntry.getValue() != null ? (String) propertyEntry.getValue() : "" );
//                if (propertyIterator.hasNext()) {
//                    csvFileOutputStream.write(",");
//                }
//            }
                csvFileOutputStream.newLine();
                // 写入文件内容
                for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext();) {
                    java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                    csvFileOutputStream.write((String) propertyEntry.getValue() != null ? (String) propertyEntry.getValue() : "" );
                    if (propertyIterator.hasNext()) {
                        csvFileOutputStream.newLine();
                    }
                }
                csvFileOutputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    csvFileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return csvFile;
        }

//    /**
//     * 整合csv工具
//     */
//    public static void main(String[] args) throws Exception {
//        // TODO Auto-generated method stub
//        // 将所有类型的尽调excel文件合并成一个excel文件
//        Properties prop = PropertiesUtil.getloadProperties();
//
//        String Path = prop.getProperty("weilian.outPutPath");
//        File file = new File(Path);
//        File[] tempList = file.listFiles();
//        String TmpList [] = new String[16];
//        System.out.println("该目录下对象个数：" + tempList.length);
//        for (int i = 0; i < tempList.length; i++) {
////            System.out.println(tempList[i].toString());
//            if(!(tempList[i].toString().contains("csv"))){
//                continue;
//            }
//            if (tempList[i].isFile()) {
//                TmpList[i] = tempList[i].toString();
//                System.out.println("文件:"+TmpList[i]+" 待处理");
//            }
//        }
//        XSSFWorkbook newExcelCreat = new XSSFWorkbook();
//        for (String fromExcelName : TmpList) {    // 遍历每个源excel文件，TmpList为源文件的名称集合
//            InputStream in = new FileInputStream(fromExcelName);
//            XSSFWorkbook fromExcel = new XSSFWorkbook(in);
//            int length = fromExcel.getNumberOfSheets();
//            if(length<=1){       //长度为1时
//                XSSFSheet oldSheet = fromExcel.getSheetAt(0);
//                XSSFSheet newSheet = newExcelCreat.createSheet(oldSheet.getSheetName());
//                copySheet(newExcelCreat, oldSheet, newSheet);
//            }else{
//                for (int i = 0; i < length; i++) {// 遍历每个sheet
//                    XSSFSheet oldSheet = fromExcel.getSheetAt(i);
//                    XSSFSheet newSheet = newExcelCreat.createSheet(oldSheet.getSheetName());
//                    copySheet(newExcelCreat, oldSheet, newSheet);
//                }
//            }
//        }
//        String allFileName = Path+ "/weilian"+System.currentTimeMillis()+".xlsx";    //定义新生成的xlx表格文件
//        FileOutputStream fileOut = new FileOutputStream(allFileName);
//        newExcelCreat.write(fileOut);
//        fileOut.flush();
//        fileOut.close();
////		// 删除各个源文件
////		for (String fromExcelName : TmpList) {// 遍历每个源excel文件
////			File Existfile = new File(fromExcelName);
////			if (Existfile.exists()) {
////				Existfile.delete();
////			}
////		}
//        System.out.println("运行结束!");
//    }

    /**
     * Sheet复制
     * @param wb
     * @param fromSheet
     * @param toSheet
     */
    public static void copySheet(XSSFWorkbook wb, XSSFSheet fromSheet, XSSFSheet toSheet) {
        mergeSheetAllRegion(fromSheet, toSheet);
        // 设置列宽
        int length = fromSheet.getRow(fromSheet.getFirstRowNum()).getLastCellNum();
        for (int i = 0; i <= length; i++) {
            toSheet.setColumnWidth(i, fromSheet.getColumnWidth(i));
        }
        for (Iterator rowIt = fromSheet.rowIterator(); rowIt.hasNext();) {
            XSSFRow oldRow = (XSSFRow) rowIt.next();
            XSSFRow newRow = toSheet.createRow(oldRow.getRowNum());
            copyRow(wb, oldRow, newRow);
        }
    }

    /**
     * 合并单元格
     * @param fromSheet
     * @param toSheet
     */
    public static void mergeSheetAllRegion(XSSFSheet fromSheet, XSSFSheet toSheet) {
        int num = fromSheet.getNumMergedRegions();
        CellRangeAddress cellR = null;
        for (int i = 0; i < num; i++) {
            cellR = fromSheet.getMergedRegion(i);
            toSheet.addMergedRegion(cellR);
        }
    }



    /**
     * 行复制功能
     * @param wb
     * @param oldRow
     * @param toRow
     */
    public static void copyRow(XSSFWorkbook wb, XSSFRow oldRow, XSSFRow toRow) {
        toRow.setHeight(oldRow.getHeight());
        for (Iterator cellIt = oldRow.cellIterator(); cellIt.hasNext();) {
            XSSFCell tmpCell = (XSSFCell) cellIt.next();
            XSSFCell newCell = toRow.createCell(tmpCell.getColumnIndex());
            copyCell(wb, tmpCell, newCell);
        }
    }

    /**
     * 复制单元格
     * @param wb
     * @param fromCell
     * @param toCell
     */
    public static void copyCell(XSSFWorkbook wb, XSSFCell fromCell, XSSFCell toCell) {
        XSSFCellStyle newstyle = wb.createCellStyle();
        copyCellStyle(fromCell.getCellStyle(), newstyle);
        //  toCell.setEncoding(fromCell.getStringCelllValue());
        // 样式
        toCell.setCellStyle(newstyle);
        if (fromCell.getCellComment() != null) {
            toCell.setCellComment(fromCell.getCellComment());
        }
        // 不同数据类型处理
        int fromCellType = fromCell.getCellType();
        toCell.setCellType(fromCellType);
        if (fromCellType == XSSFCell.CELL_TYPE_NUMERIC) {
            if (XSSFDateUtil.isCellDateFormatted(fromCell)) {
                toCell.setCellValue(fromCell.getDateCellValue());
            } else {
                toCell.setCellValue(fromCell.getNumericCellValue());
            }
        } else if (fromCellType == XSSFCell.CELL_TYPE_STRING) {
            toCell.setCellValue(fromCell.getRichStringCellValue());
        } else if (fromCellType == XSSFCell.CELL_TYPE_BLANK) {
            // nothing21
        } else if (fromCellType == XSSFCell.CELL_TYPE_BOOLEAN) {
            toCell.setCellValue(fromCell.getBooleanCellValue());
        } else if (fromCellType == XSSFCell.CELL_TYPE_ERROR) {
            toCell.setCellErrorValue(fromCell.getErrorCellValue());
        } else if (fromCellType == XSSFCell.CELL_TYPE_FORMULA) {
            toCell.setCellFormula(fromCell.getCellFormula());
        } else { // nothing29
        }

    }

    public static void copyCellStyle(XSSFCellStyle fromStyle, XSSFCellStyle toStyle) {

        toStyle.cloneStyleFrom(fromStyle);// 此一行代码搞定
        // 下面统统不用
        /*
         * //对齐方式 toStyle.setAlignment(fromStyle.getAlignment()); //边框和边框颜色
         * toStyle.setBorderBottom(fromStyle.getBorderBottom());
         * toStyle.setBorderLeft(fromStyle.getBorderLeft());
         * toStyle.setBorderRight(fromStyle.getBorderRight());
         * toStyle.setBorderTop(fromStyle.getBorderTop());
         * toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
         * toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
         * toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
         * toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor()); //背景和前景
         * //toStyle.setFillPattern(fromStyle.getFillPattern());
         * //填充图案，不起作用，转为黑色
         * toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
         * //不起作用
         * toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());
         * toStyle.setDataFormat(fromStyle.getDataFormat()); //数据格式
         * //toStyle.setFont(fromStyle.getFont()); //不起作用
         * toStyle.setHidden(fromStyle.getHidden());
         * toStyle.setIndention(fromStyle.getIndention());//首行缩进
         * toStyle.setLocked(fromStyle.getLocked());
         * toStyle.setRotation(fromStyle.getRotation());//旋转
         * toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());
         * //垂直对齐 toStyle.setWrapText(fromStyle.getWrapText()); //文本换行
         */
    }


    public class XSSFDateUtil extends DateUtil {

    }


}

