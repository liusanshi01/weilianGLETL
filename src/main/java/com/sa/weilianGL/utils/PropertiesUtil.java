package com.sa.weilianGL.utils;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

public class PropertiesUtil {

    @SneakyThrows
    public static Properties getloadProperties(){
        Properties prop = new Properties();
        prop.load(new FileInputStream("./conf/weilian.properties"));
//        System.out.println(prop);
        return prop;
    }

    public static void main(String[] args) {
        String beginDatestr = "2021-01-25";
        Date beginDate = DateUtil.parse(beginDatestr);

        String today = DateUtil.today();
        Date nowdate = DateUtil.parse(today);
        //相差一个月，31天
        long betweenDay = DateUtil.between(beginDate, nowdate, DateUnit.DAY);
        System.out.println(betweenDay);
    }
}
