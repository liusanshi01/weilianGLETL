package com.sa.weilianGL.api;

import com.sa.weilianGL.utils.HttpClientUtil;
import com.sa.weilianGL.utils.PropertiesUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class weilianMarketETLMethods {

    Properties prop = PropertiesUtil.getloadProperties();
    String url = prop.getProperty("weilian.url");

    public String marketActivationsETL(String fromDate, String toDate, String profilesDate,String lastresult){

        String req = "{\n" +
                "\"bookmarkid\": \"3842\",\n" +
                "\"bucket_params\": {},\n" +
                "\"by_fields\": [],\n" +
                "\"detail_and_rollup\": \"true\",\n" +
                "\"enable_detail_follow_rollup_by_values_rank\": \"true\",\n" +
                "\"fromDash\": {\"id\":447,\"type\":\"nolmal\"},\n" +
                "\"from_date\": \"$$from_date\",\n" +
                "\"include_today\": \"true\",\n" +
                "\"filter\":{\"conditions\":[{\n" +
                "                \"$$render_index\":1,\n" +
                "                \"$$searchValue\":\"激活设备\",\n" +
                "                \"field\":\"user.user_tag_jhsbyysc@$$profiles_date 00:00:00\",\n" +
                "                \"function\":\"equal\",\n" +
                "                \"params\":[\"激活设备--应用市场\"]\n" +
                "                }]},\n" +
                "\"measures\": [           \n" +
                "\t\t\t{\n" +
                "            \"aggregator\":\"unique\",\n" +
                "            \"editName\":\"总用户数\",\n" +
                "            \"event_name\":\"AppInstall\",\n" +
                "            \"name\":\"总用户数\"},\n"+
                "\t\t\t{\n" +
                "            \"aggregator\":\"unique\",\n" +
                "            \"editName\":\"付费数\",\n" +
                "            \"event_name\":\"rechargeArrival\",\n" +
                "            \"name\":\"付费数\"},\n" +
                "            {\n" +
                "            \"aggregator\":\"SUM\",\n" +
                "            \"editName\":\"充值额\",\n" +
                "            \"event_name\":\"rechargeArrival\",\n" +
                "            \"field\": \"event.rechargeArrival_fixutm.amount\",\n" +
                "            \"name\":\"充值额\"} ,\n" +
                "            {\n" +
                "            \"aggregator\":\"uniqAvg\",\n" +
                "            \"editName\":\"人均金额ARPU\",\n" +
                "            \"event_name\":\"rechargeArrival\",\n" +
                "            \"field\": \"event.rechargeArrival_fixutm.amount\",\n" +
                "            \"name\":\"人均金额ARPU\"} ,\n" +
                "            {\n" +
                "            \"editName\":\"当日LTV\",\n" +
                "            \"events\":[\"rechargeArrival_fixutm\", \"AppInstall\"],\n" +
                "            \"expression\":\"sum(event.rechargeArrival.amount)/uniqcount(event.AppInstall)|%2f\",\n" +
                "            \"expression_denominator_without_group\": false,\n" +
                "            \"format\": \"%2f\",\n" +
                "            \"isUnSaved\": true,\n" +
                "            \"name\":\"当日LTV\"} \n" +
                "\t\t],\n" +
                "\"request_id\": \"1614147343463:496674\",\n" +
                "\"server_time_zone\": \"\",\n" +
                "\"sub_task_type\": \"SEGMENTATION\",\n" +
                "\"time_zone_mode\": \"\",\n" +
                "\"to_date\": \"$$to_date\",\n" +
                "\"unit\": \"day\",\n" +
                "\"use_cache\": \"true\",\n" +
                "\"valueTypes\":\"number\"}";

        req = req.replaceAll("\\$\\$from_date",fromDate).replaceAll("\\$\\$to_date",toDate).replaceAll("\\$\\$profiles_date",profilesDate);

        String result = HttpClientUtil.sendPost(url, req);
        String[] resStrs = result.split("\n");
//        ArrayList<String> arr2 = new ArrayList(){};

        if(lastresult == null){
            for (String res2 : resStrs) {
                if (res2.contains("00:00:00")) {
                    return res2;
                }
            }
        }

        if(!(lastresult == null)){
            for (String res2 : resStrs) {
                if (res2.contains("合计")) {
                    String[] res2s = res2.split(",");
                    return lastresult+","+res2s[2]+","+res2s[3]+","+res2s[4]+","+res2s[5];
                }
            }

        }
        return "";
    }

    public String marketRegisterETL(String fromDate, String toDate, String profilesDate,String lastresult) {

        String req = "{\n" +
                "\"bookmarkid\": \"3847\",\n" +
                "\"bucket_params\": {},\n" +
                "\"by_fields\": [],\n" +
                "\"detail_and_rollup\": \"true\",\n" +
                "\"enable_detail_follow_rollup_by_values_rank\": \"true\",\n" +
                "\"fromDash\": {\"id\":447,\"type\":\"nolmal\"},\n" +
                "\"from_date\": \"$$from_date\",\n" +
                "\"include_today\": \"true\",\n" +
                "\"filter\":{\"conditions\":[{\n" +
                "                \"$$render_index\":1,\n" +
                "                \"$$searchValue\":\"新增\",\n" +
                "                \"field\":\"user.user_tag_xzyhyysc@$$profiles_date 00:00:00\",\n" +
                "                \"function\":\"equal\",\n" +
                "                \"params\":[\"新增用户--应用市场\"]\n" +
                "                }]},\n" +
                "\"measures\": [           \n" +
                "\t\t\t{\n" +
                "            \"aggregator\":\"unique\",\n" +
                "            \"editName\":\"新注册用户数\",\n" +
                "            \"event_name\":\"registerSuccess\",\n" +
                "            \"name\":\"总用户数\"},\n" +
                "\t\t\t{\n" +
                "            \"aggregator\":\"unique\",\n" +
                "            \"editName\":\"付费数\",\n" +
                "            \"event_name\":\"rechargeArrival\",\n" +
                "            \"name\":\"付费数\"},\n" +
                "            {\n" +
                "            \"aggregator\":\"SUM\",\n" +
                "            \"editName\":\"充值额\",\n" +
                "            \"event_name\":\"rechargeArrival\",\n" +
                "            \"field\": \"event.rechargeArrival_fixutm.amount\",\n" +
                "            \"name\":\"充值额\"} ,\n" +
                "            {\n" +
                "            \"aggregator\":\"uniqAvg\",\n" +
                "            \"editName\":\"人均金额ARPU\",\n" +
                "            \"event_name\":\"rechargeArrival\",\n" +
                "            \"field\": \"event.rechargeArrival_fixutm.amount\",\n" +
                "            \"name\":\"人均金额ARPU\"} ,\n" +
                "            {\n" +
                "            \"editName\":\"当日LTV\",\n" +
                "            \"events\":[\"rechargeArrival_fixutm\", \"AppInstall\"],\n" +
                "            \"expression\":\"sum(event.rechargeArrival.amount)/uniqcount(event.AppInstall)|%2f\",\n" +
                "            \"expression_denominator_without_group\": false,\n" +
                "            \"format\": \"%2f\",\n" +
                "            \"isUnSaved\": true,\n" +
                "            \"name\":\"当日LTV\"} \n" +
                "\t\t],\n" +
                "\"request_id\": \"1614147343463:496674\",\n" +
                "\"server_time_zone\": \"\",\n" +
                "\"sub_task_type\": \"SEGMENTATION\",\n" +
                "\"time_zone_mode\": \"\",\n" +
                "\"to_date\": \"$$to_date\",\n" +
                "\"unit\": \"day\",\n" +
                "\"use_cache\": \"true\",\n" +
                "\"valueTypes\":\"number\"}";

        req = req.replaceAll("\\$\\$from_date", fromDate).replaceAll("\\$\\$to_date", toDate).replaceAll("\\$\\$profiles_date", profilesDate);

        String result = HttpClientUtil.sendPost(url, req);
        String[] resStrs = result.split("\n");
//        ArrayList<String> arr2 = new ArrayList(){};

        if (lastresult == null) {
            for (String res2 : resStrs) {
                if (res2.contains("00:00:00")) {
                    return res2;
                }
            }
        }

        if (!(lastresult == null)) {
            for (String res2 : resStrs) {
                if (res2.contains("合计")) {
                    String[] res2s = res2.split(",");
                    return lastresult + "," + res2s[2] + "," + res2s[3] + "," + res2s[4] + "," + res2s[5];
                }
            }

        }
        return "";
    }



    public String marketBackflowETL(String fromDate, String toDate, String profilesDate,String lastresult) {

        String req = "{\n" +
                "\"bookmarkid\": \"3852\",\n" +
                "\"bucket_params\": {},\n" +
                "\"by_fields\": [],\n" +
                "\"detail_and_rollup\": \"true\",\n" +
                "\"enable_detail_follow_rollup_by_values_rank\": \"true\",\n" +
                "\"fromDash\": {\"id\":447,\"type\":\"nolmal\"},\n" +
                "\"from_date\": \"$$from_date\",\n" +
                "\"include_today\": \"true\",\n" +
                "\"filter\":{\"conditions\":[{\n" +
                "                \"$$render_index\":1,\n" +
                "                \"$$searchValue\":\"回流\",\n" +
                "                \"field\":\"user.user_tag_xzyhyysc@$$profiles_date 00:00:00\",\n" +
                "                \"function\":\"equal\",\n" +
                "                \"params\":[\"回流用户--应用市场\"]\n" +
                "                }]},\n" +
                "\"measures\": [           \n" +
                "\t\t\t{\n" +
                "            \"aggregator\":\"unique\",\n" +
                "            \"editName\":\"回流用户数\",\n" +
                "            \"event_name\":\"login\",\n" +
                "            \"name\":\"回流用户数\"},\n" +
                "\t\t\t{\n" +
                "            \"aggregator\":\"unique\",\n" +
                "            \"editName\":\"付费数\",\n" +
                "            \"event_name\":\"rechargeArrival\",\n" +
                "            \"name\":\"付费数\"},\n" +
                "            {\n" +
                "            \"aggregator\":\"SUM\",\n" +
                "            \"editName\":\"充值额\",\n" +
                "            \"event_name\":\"rechargeArrival\",\n" +
                "            \"field\": \"event.rechargeArrival_fixutm.amount\",\n" +
                "            \"name\":\"充值额\"} ,\n" +
                "            {\n" +
                "            \"aggregator\":\"uniqAvg\",\n" +
                "            \"editName\":\"人均金额ARPU\",\n" +
                "            \"event_name\":\"rechargeArrival\",\n" +
                "            \"field\": \"event.rechargeArrival_fixutm.amount\",\n" +
                "            \"name\":\"人均金额ARPU\"} ,\n" +
                "            {\n" +
                "            \"editName\":\"当日LTV\",\n" +
                "            \"events\":[\"rechargeArrival_fixutm\", \"AppInstall\"],\n" +
                "            \"expression\":\"sum(event.rechargeArrival.amount)/uniqcount(event.AppInstall)|%2f\",\n" +
                "            \"expression_denominator_without_group\": false,\n" +
                "            \"format\": \"%2f\",\n" +
                "            \"isUnSaved\": true,\n" +
                "            \"name\":\"当日LTV\"} \n" +
                "\t\t],\n" +
                "\"request_id\": \"1614147343463:496674\",\n" +
                "\"server_time_zone\": \"\",\n" +
                "\"sub_task_type\": \"SEGMENTATION\",\n" +
                "\"time_zone_mode\": \"\",\n" +
                "\"to_date\": \"$$to_date\",\n" +
                "\"unit\": \"day\",\n" +
                "\"use_cache\": \"true\",\n" +
                "\"valueTypes\":\"number\"}";

        req = req.replaceAll("\\$\\$from_date", fromDate).replaceAll("\\$\\$to_date", toDate).replaceAll("\\$\\$profiles_date", profilesDate);

        String result = HttpClientUtil.sendPost(url, req);
        String[] resStrs = result.split("\n");
//        ArrayList<String> arr2 = new ArrayList(){};

        if (lastresult == null) {
            for (String res2 : resStrs) {
                if (res2.contains("00:00:00")) {
                    return res2;
                }
            }
        }

        if (!(lastresult == null)) {
            for (String res2 : resStrs) {
                if (res2.contains("合计")) {
                    String[] res2s = res2.split(",");
                    return lastresult + "," + res2s[2] + "," + res2s[3] + "," + res2s[4] + "," + res2s[5];
                }
            }

        }
        return "";
    }
}
