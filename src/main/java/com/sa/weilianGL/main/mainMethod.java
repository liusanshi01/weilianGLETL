package com.sa.weilianGL.main;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.sa.weilianGL.achieve.*;
import com.sa.weilianGL.utils.CSVUtil;
import com.sa.weilianGL.utils.MailUtil;
import com.sa.weilianGL.utils.PropertiesUtil;
import com.sa.weilianGL.utils.jxlUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

@Slf4j
public class mainMethod {

    public static void main(String[] args) {
        log.info("任务开始!");
        Properties properties = PropertiesUtil.getloadProperties();
        String ToMail = properties.getProperty("weilian.Tomail");
        String outPutPath = properties.getProperty("weilian.outPutPath");
        String today = DateUtil.today();
        Date nowdate = DateUtil.parse(today);
        int num = 30;//正常跑周期
        //是否开启跑全量。
        String allDateData = properties.getProperty("weilian.allDateData");
        if(allDateData.equals("true")){
            String beginDatestr = "2021-01-25";
            Date beginDate = DateUtil.parse(beginDatestr);
            //相差一个月，31天
            long betweenDay = DateUtil.between(beginDate, nowdate, DateUnit.DAY);
            num = (int)betweenDay;
            log.info("全量相差多少天："+num);
        }

        //1.渠道激活
        HashMap<String, String> allmap1 = new HashMap<>();
        for (int i = 1; i <= num ; i++) {
            DateTime newDate = DateUtil.offsetDay(nowdate, -i);
            String newDatestr = newDate.toString().substring(0,10).trim();
            HashMap<String, String> map = activateETLMain.activatemain(newDatestr);
            allmap1.putAll(map);
        }
        CSVUtil.createaCSVFile(allmap1,outPutPath,"渠道激活");
        log.info("渠道激活完成");


        //2.渠道注册
//        registerETLMain.registermain();
        HashMap<String, String> allmap2 = new HashMap<>();
        for (int i = 1; i <= num ; i++) {
            DateTime newDate = DateUtil.offsetDay(nowdate, -i);
            String newDatestr = newDate.toString().substring(0,10).trim();
            HashMap<String, String> map = registerETLMain.registermain(newDatestr);;
            allmap2.putAll(map);
        }
        CSVUtil.createrCSVFile(allmap2,outPutPath,"渠道注册");
        log.info("渠道注册完成");

        //3.渠道回流
        HashMap<String, String> allmap3 = new HashMap<>();
        for (int i = 1; i <= num ; i++) {
            DateTime newDate = DateUtil.offsetDay(nowdate, -i);
            String newDatestr = newDate.toString().substring(0,10).trim();
            HashMap<String, String> map = backflowETLMain.backflowmain(newDatestr);;
            allmap3.putAll(map);
        }
        CSVUtil.createbCSVFile(allmap3,outPutPath,"渠道回流");
        log.info("渠道回流完成");


        //4.应用市场激活
        HashMap<Integer, String> allmap4 = new HashMap<>();
        for (int i = 1; i <= num ; i++) {
            DateTime newDate = DateUtil.offsetDay(nowdate, -i);
            String newDatestr = newDate.toString().substring(0,10).trim();
            String result = marketActivateETLMain.marketActovatemain(newDatestr);
            allmap4.put(i,result);
        }
        CSVUtil.createMaCSVFile(allmap4,outPutPath,"应用市场激活");


        //5.应用市场注册

        HashMap<Integer, String> allmap5 = new HashMap<>();
        for (int i = 1; i <= num ; i++) {
            DateTime newDate = DateUtil.offsetDay(nowdate, -i);
            String newDatestr = newDate.toString().substring(0,10).trim();
            String result = marketRegisterETLMain.marketRegistermain(newDatestr);
            allmap5.put(i,result);
        }
        CSVUtil.createMrCSVFile(allmap5,outPutPath,"应用市场注册");

        //6.应用市场回流

        HashMap<Integer, String> allmap6 = new HashMap<>();
        for (int i = 1; i <= num ; i++) {
            DateTime newDate = DateUtil.offsetDay(nowdate, -i);
            String newDatestr = newDate.toString().substring(0,10).trim();
            String result = marketBackflowETLMain.marketBackflowmain(newDatestr);
            allmap6.put(i,result);
        }
        CSVUtil.createMbCSVFile(allmap6,outPutPath,"应用市场回流");


        //7.把6个csv文件汇总
        jxlUtil.SummaryCsv();
        log.info("文件汇总完成");

        //8.发送邮件@
        MailUtil.sendMail(ToMail);
        log.info("发送邮件完成");

    }
}
