package com.sa.weilianGL.achieve;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.sa.weilianGL.api.weilianETLMethods;
import com.sa.weilianGL.utils.CSVUtil;
import com.sa.weilianGL.utils.PropertiesUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

/***
 * 获取新注册用户数
 */
public class registerETLMain {

    public static HashMap registermain(String toDate) {
        Date date = DateUtil.parse(toDate);
        DateTime profilesDate1 = DateUtil.offsetDay(date, 1);
        String profilesDate = profilesDate1.toString().substring(0,10).trim();

        //判断次日日期是否比当前时间小
        //当前日期字符串，格式：yyyy-MM-dd
        String today = DateUtil.today();
        Date nowdate = DateUtil.parse(today);
        long now = nowdate.getTime() ;


        weilianETLMethods welETL = new weilianETLMethods();

        /**
         * 1、获取新注册用户数
         */
        HashMap<String, String> allmap = welETL.registrations(toDate,profilesDate);

        /**
         * 2、获取当日付费数、当日付费金额、当日APRU、当日LTV 并合并
         */
        //合计,tt,yuanmeng-kaiyu-tt-puxie,yuanmeng-kaiyu-tt-puxie02,235,25750,109.57,187.96
        DateTime newDate = profilesDate1;
        long newnow = newDate.getTime();
        if(now >= newnow){
            welETL.registrationsETL(toDate,toDate,profilesDate,allmap);
        }

        /**
         * 3、获取1日付费数、1日付费金额、1日APRU、1日LTV 并合并
         */
        DateTime newDate1 = DateUtil.offsetDay(date, 1);
        long new1now = newDate1.getTime() + 86400000;
        if(now >= new1now){
            String toDateNew1 = newDate1.toString().substring(0,10).trim();
            welETL.registrationsETL(toDate,toDateNew1,profilesDate,allmap);
        }


        /**
         * 4、获取3日付费数、3日付费金额、3日APRU、3日LTV 并合并
         */

        DateTime newDate3 = DateUtil.offsetDay(date, 2);
        long new3now = newDate3.getTime() + 86400000;
        if(now >= new3now){
            String toDateNew3 = newDate3.toString().substring(0,10).trim();
            welETL.registrationsETL(toDate,toDateNew3,profilesDate,allmap);
        }


        /**
         * 5、获取5日付费数、5日付费金额、5日APRU、5日LTV 并合并
         */
        DateTime newDate5 = DateUtil.offsetDay(date, 4);
        long new5now = newDate5.getTime() + 86400000;
        if(now >= new5now){
            String toDateNew5 = newDate5.toString().substring(0,10).trim();
            welETL.registrationsETL(toDate,toDateNew5,profilesDate,allmap);
        }

        /**
         * 6、获取7日付费数、7日付费金额、7日APRU、7日LTV 并合并
         */
        DateTime newDate7 = DateUtil.offsetDay(date, 6);
        long new7now = newDate7.getTime() + 86400000;
        if(now >= new7now){
            String toDateNew7 = newDate7.toString().substring(0,10).trim();
            welETL.registrationsETL(toDate,toDateNew7,profilesDate,allmap);
        }

        /**
         * 7、获取14日付费数、14日付费金额、14日APRU、14日LTV 并合并
         */
        DateTime newDate14 = DateUtil.offsetDay(date, 13);
        long new14now = newDate14.getTime() + 86400000;
        if(now >= new14now){
            String toDateNew14 = newDate14.toString().substring(0,10).trim();
            welETL.registrationsETL(toDate,toDateNew14,profilesDate,allmap);
        }

        /**
         * 8、获取30日付费数、30日付费金额、30日APRU、30日LTV 并合并
         */
        DateTime newDate30 = DateUtil.offsetDay(date, 29);
        long new30now = newDate30.getTime() + 86400000;
        if(now >= new30now){
            String toDateNew30 = newDate30.toString().substring(0,10).trim();
            welETL.registrationsETL(toDate,toDateNew30,profilesDate,allmap);
        }

        //重新遍历
        HashMap newmap = new HashMap<>();
        for (HashMap.Entry<String, String> entry : allmap.entrySet()) {
            newmap.put(entry.getKey()+toDate,entry.getValue());
        }
        return newmap;

//        /**
//         * sink - csv文档.
//         */
//        CSVUtil.createrCSVFile(allmap,outPutPath,"渠道注册");


    }
}
