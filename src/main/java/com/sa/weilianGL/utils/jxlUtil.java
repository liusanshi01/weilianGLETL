package com.sa.weilianGL.utils;

import java.io.*;
import java.util.*;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import lombok.SneakyThrows;

public class jxlUtil {


    /**
     * 获得目录下所有csv文件名字
     */
    public static ArrayList<String> getCsvNames(){
        // 将所有类型的尽调excel文件合并成一个excel文件
        Properties prop = PropertiesUtil.getloadProperties();

        ArrayList<String> names = new ArrayList<String>();;
        String Path = prop.getProperty("weilian.outPutPath");
        File file = new File(Path);
        File[] tempList = file.listFiles();
        String TmpList [] = new String[16];
        System.out.println("该目录下对象个数：" + tempList.length);
        for (int i = 0; i < tempList.length; i++) {
//            System.out.println(tempList[i].toString());
            if(!(tempList[i].toString().contains("csv"))){
                continue;
            }
            if (tempList[i].isFile()) {
                TmpList[i] = tempList[i].toString();
                System.out.println("文件:"+TmpList[i]+" 待处理");
                names.add(TmpList[i]);
            }
        }
        return names;
    }

    /***
     * 传入一个csv文件名 读取一次csv文件。
     */
    public static ArrayList<String> readCsv (String csvPath){

        ArrayList<String> arr = new ArrayList<>();
        try {
            //(文件完整路径),编码格式
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvPath), "utf-8"));//GBK
//                 reader.readLine();//显示标题行,没有则注释掉
//                 System.out.println(reader.readLine());
            String line = null;

            while((line=reader.readLine())!=null){
//                String item[] = line.split(",");//CSV格式文件时候的分割符,我使用的是,号
//                String last = item[item.length-1];//CSV中的数据,如果有标题就不用-1
                arr.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arr;
    }

    /**
     *  做一个读取csv文件 就写一次sheet的方法。
     *
     */
    public static void writeExcel(String name,int ii,// 创建Excel工作薄
                                  WritableWorkbook wwb) {


        ArrayList<String> arr = readCsv(name);
        String[] arrs = name.split("/");
        String sheetName = arrs[arrs.length-1].replaceAll(".csv","");

        try {

            // 添加第一个工作表并设置第一个Sheet的名字
            WritableSheet sheets = wwb.createSheet(sheetName, ii);
//            WritableSheet sheet2 = wwb.createSheet(sheetName+ii, ii);
//            WritableSheet sheet3 = wwb.createSheet("xxx"+ii, ii);
//            WritableSheet sheet4 = wwb.createSheet("ssss"+ii, ii);
            // 设置单元格属性
            WritableCellFormat wc = new WritableCellFormat();
            // 设置居中
            wc.setAlignment(Alignment.CENTRE);
            // 设置边框线
            wc.setBorder(Border.ALL, BorderLineStyle.THIN);


            Label label = null;
            int i = 0;
            for (String str: arr) {
                //Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
                // 在Label对象的子对象中指明单元格的位置和内容
                // label = new Label(i, 0, title[i]);
                int j = 0;
                String[] strs = str.split(",");
                for (String string:strs) {

                    if(i == 0){
                        label = new Label(j++, i, string, getHeader());
                        sheets.addCell(label);
                    }
                    if(i != 0){
                        label = new Label(j++, i, string, wc);
                        sheets.addCell(label);
                    }

                }
                // 设置列宽
                sheets.setColumnView(i, 12);
                // sheets.setColumnView(4, 100);
                // 将定义好的单元格添加到工作表中
//                sheets.addCell(label);
                i++;
            }
            // 写入数据
//            wwb.write();
            // 关闭文件

//            wwb.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
//        return wwb;

    }

    public static WritableCellFormat getHeader() {
        // 定义字体
        WritableFont font = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD);
        try {
            // 黑色字体
            font.setColour(jxl.format.Colour.BLACK);
        }
        catch (WriteException e1) {
            e1.printStackTrace();
        }
        WritableCellFormat format = new WritableCellFormat(font);
        try {
            // 左右居中
            format.setAlignment(jxl.format.Alignment.CENTRE);
            // 上下居中
            format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            // 黑色边框
            format.setBorder(Border.ALL, BorderLineStyle.THIN, jxl.format.Colour.BLACK);
            // 黄色背景
            format.setBackground(jxl.format.Colour.YELLOW);
        }
        catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    @SneakyThrows
    public static void SummaryCsv() {

        Properties prop = PropertiesUtil.getloadProperties();
        String path = prop.getProperty("weilian.outPutPath") + "/weilian.xls";

        ArrayList<String> names = getCsvNames();
        Collections.sort(names, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                // 升序
                //return o1.getAge()-o2.getAge();
                if(o1.contains("渠道") && o2.contains("渠道")){
                    return 0;
                }

                if(o1.contains("渠道") ){
                    return -1;
                }

                if(o2.contains("渠道")){
                    return 1;
                }

                return 0;
                // 降序
                // return o2.getAge()-o1.getAge();
                // return o2.getAge().compareTo(o1.getAge());
            }
        });

        // 创建Excel工作薄
        WritableWorkbook wwb = null;

        OutputStream os = new FileOutputStream(path);
        wwb = Workbook.createWorkbook(os);

        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            writeExcel(name,i+1,wwb);
        }

        wwb.write();
        //关闭
        wwb.close();
    }

}
