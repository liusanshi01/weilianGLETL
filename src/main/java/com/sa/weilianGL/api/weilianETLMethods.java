package com.sa.weilianGL.api;

import com.sa.weilianGL.utils.HttpClientUtil;
import com.sa.weilianGL.utils.PropertiesUtil;

import java.util.*;

public class weilianETLMethods {

    Properties prop = PropertiesUtil.getloadProperties();
    String url = prop.getProperty("weilian.url");

    public HashMap<String, String> activations(String toDate,String profilesDate){
        String req = "{\n" +
                "\"bookmarkid\": \"3677\",\n" +
                "\"bucket_params\": {},\n" +
                "\"by_fields\": [\"event.$Anything.$utm_source\", \"event.$Anything.$utm_campaign\", \"event.$Anything.$utm_content\"],\n" +
                "\"detail_and_rollup\": \"true\",\n" +
                "\"enable_detail_follow_rollup_by_values_rank\": \"true\",\n" +
                "\"fromDash\": {\"id\":431,\"type\":\"nolmal\"},\n" +
                "\"from_date\": \"$$to_date\",\n" +
                "\"include_today\": \"true\",\n" +
                "\"measures\": [{\n" +
                "            \"aggregator\":\"unique\",\n" +
                "            \"editName\":\"日激活用户数\",\n" +
                "            \"event_name\":\"AppInstall\",\n" +
                "            \"filter\":{\"conditions\":[{\n" +
                "                \"$$render_index\":1,\n" +
                "                \"$$searchValue\":\"激活\",\n" +
                "                \"field\":\"user.user_tag_anzhuangqudaofenbu@$$profiles_date 00:00:00\",\n" +
                "                \"function\":\"isSet\",\n" +
                "                \"params\":[]\n" +
                "                },\n" +
                "                {\n" +
                "                \"$$render_index\":2,\n" +
                "                \"$$searchValue\":\"内容\",\n" +
                "                \"field\":\"event.$Anything.$utm_content\",\n" +
                "                \"function\":\"isSet\",\n" +
                "                \"params\":[]\n" +
                "                }]},\n" +
                "            \"name\":\"日激活用户数\"}],\n" +
                "\"request_id\": \"1614076821780\",\n" +
                "\"server_time_zone\": \"\",\n" +
                "\"sub_task_type\": \"SEGMENTATION\",\n" +
                "\"time_zone_mode\": \"\",\n" +
                "\"to_date\": \"$$to_date\",\n" +
                "\"unit\": \"day\",\n" +
                "\"use_cache\": \"false\"}";

        req = req.replaceAll("\\$\\$to_date",toDate).replaceAll("\\$\\$profiles_date",profilesDate);

//        System.out.println(req);
        HashMap<String, String> allmap = new HashMap();
        String res = HttpClientUtil.sendPost(url, req);
//        System.out.println(res);
        String[] resStrs = res.split("\n");
        String dateStr = null;

        for (String res2 : resStrs) {
            if (res2.contains("00:00:00")) {
                dateStr = res2.split(",")[0];
                break;
            }
        }

        for (String res2 : resStrs) {
            if (res2.contains("合计")) {
                String key = res2.split(",")[3];
                allmap.put(key,res2.replaceAll("合计", dateStr));
            }
        }

        return allmap;
    }

    public HashMap<String, String> registrations(String toDate,String profilesDate){
        String req = "{\n" +
                "\"bookmarkid\": \"3680\",\n" +
                "\"bucket_params\": {},\n" +
                "\"by_fields\": [\"event.$Anything.$utm_source\", \"event.$Anything.$utm_campaign\", \"event.$Anything.$utm_content\"],\n" +
                "\"detail_and_rollup\": \"true\",\n" +
                "\"enable_detail_follow_rollup_by_values_rank\": \"true\",\n" +
                "\"fromDash\": {\"id\":431,\"type\":\"nolmal\"},\n" +
                "\"from_date\": \"$$to_date\",\n" +
                "\"include_today\": \"true\",\n" +
                "\"measures\": [{\n" +
                "            \"aggregator\":\"unique\",\n" +
                "            \"editName\":\"新注册用户数\",\n" +
                "            \"event_name\":\"register_fixutm\",\n" +
                "            \"filter\":{\"conditions\":[{\n" +
                "                \"$$render_index\":1,\n" +
                "                \"$$searchValue\":\"新注册用户数\",\n" +
                "                \"field\":\"user.user_tag_laiyuan_zhuce@$$profiles_date 00:00:00\",\n" +
                "                \"function\":\"isSet\",\n" +
                "                \"params\":[]\n" +
                "                },\n" +
                "                {\n" +
                "                \"$$render_index\":2,\n" +
                "                \"$$searchValue\":\"内容\",\n" +
                "                \"field\":\"event.$Anything.$utm_content\",\n" +
                "                \"function\":\"isSet\",\n" +
                "                \"params\":[]\n" +
                "                }]},\n" +
                "            \"name\":\"新注册用户数\"}],\n" +
                "\"request_id\": \"1614235087845\",\n" +
                "\"server_time_zone\": \"\",\n" +
                "\"sub_task_type\": \"SEGMENTATION\",\n" +
                "\"time_zone_mode\": \"\",\n" +
                "\"to_date\": \"$$to_date\",\n" +
                "\"unit\": \"day\",\n" +
                "\"use_cache\": \"false\"}";

        req = req.replaceAll("\\$\\$to_date",toDate).replaceAll("\\$\\$profiles_date",profilesDate);

//        System.out.println(req);
        HashMap<String, String> allmap = new HashMap();
        String res = HttpClientUtil.sendPost(url, req);
//        System.out.println(res);
        String[] resStrs = res.split("\n");
        String dateStr = null;

        for (String res2 : resStrs) {
            if (res2.contains("00:00:00")) {
                dateStr = res2.split(",")[0];
                break;
            }
        }

        for (String res2 : resStrs) {
            if (res2.contains("合计")) {
                String key = res2.split(",")[3];
                allmap.put(key,res2.replaceAll("合计", dateStr));
            }
        }

        return allmap;
    }

    public HashMap<String, String> backflows(String toDate,String profilesDate){
        String req = "{\n" +
                "\"bookmarkid\": \"3683\",\n" +
                "\"bucket_params\": {},\n" +
                "\"by_fields\": [\"event.$Anything.$utm_source\", \"event.$Anything.$utm_campaign\", \"event.$Anything.$utm_content\"],\n" +
                "\"detail_and_rollup\": \"true\",\n" +
                "\"enable_detail_follow_rollup_by_values_rank\": \"true\",\n" +
                "\"fromDash\": {\"id\":431,\"type\":\"nolmal\"},\n" +
                "\"from_date\": \"$$to_date\",\n" +
                "\"include_today\": \"true\",\n" +
                "\"measures\": [{\n" +
                "            \"aggregator\":\"unique\",\n" +
                "            \"editName\":\"回流\",\n" +
                "            \"event_name\":\"login_fixutm\",\n" +
                "            \"filter\":{\"conditions\":[{\n" +
                "                \"$$render_index\":1,\n" +
                "                \"$$searchValue\":\"回流\",\n" +
                "                \"field\":\"user.user_tag_laiyuan_huiliu@$$profiles_date 00:00:00\",\n" +
                "                \"function\":\"isSet\",\n" +
                "                \"params\":[]\n" +
                "                },\n" +
                "                {\n" +
                "                \"$$render_index\":2,\n" +
                "                \"$$searchValue\":\"内容\",\n" +
                "                \"field\":\"event.$Anything.$utm_content\",\n" +
                "                \"function\":\"isSet\",\n" +
                "                \"params\":[]\n" +
                "                }]},\n" +
                "            \"name\":\"回流\"}],\n" +
                "\"request_id\": \"1614235087845\",\n" +
                "\"server_time_zone\": \"\",\n" +
                "\"sub_task_type\": \"SEGMENTATION\",\n" +
                "\"time_zone_mode\": \"\",\n" +
                "\"to_date\": \"$$to_date\",\n" +
                "\"unit\": \"day\",\n" +
                "\"use_cache\": \"false\"}";

        req = req.replaceAll("\\$\\$to_date",toDate).replaceAll("\\$\\$profiles_date",profilesDate);

//        System.out.println(req);
        HashMap<String, String> allmap = new HashMap();
        String res = HttpClientUtil.sendPost(url, req);
//        System.out.println(res);
        String[] resStrs = res.split("\n");
        String dateStr = null;

        for (String res2 : resStrs) {
            if (res2.contains("00:00:00")) {
                dateStr = res2.split(",")[0];
                break;
            }
        }

        for (String res2 : resStrs) {
            if (res2.contains("合计")) {
                String key = res2.split(",")[3];
                allmap.put(key,res2.replaceAll("合计", dateStr));
            }
        }

        return allmap;
    }

    public void activationsETL(String fromDate, String toDate, String profilesDate, HashMap<String, String> allmap){

        String req = "{\n" +
                "\"bookmarkid\": \"3679\",\n" +
                "\"bucket_params\": {},\n" +
                "\"by_fields\": [\"event.$Anything.$utm_source\", \"event.$Anything.$utm_campaign\", \"event.$Anything.$utm_content\"],\n" +
                "\"detail_and_rollup\": \"true\",\n" +
                "\"enable_detail_follow_rollup_by_values_rank\": \"true\",\n" +
                "\"fromDash\": {\"id\":431,\"type\":\"nolmal\"},\n" +
                "\"from_date\": \"$$from_date\",\n" +
                "\"include_today\": \"true\",\n" +
                "\"filter\":{\"conditions\":[{\n" +
                "                \"$$render_index\":1,\n" +
                "                \"field\":\"user.user_tag_anzhuangqudaofenbu@$$profiles_date 00:00:00\",\n" +
                "                \"function\":\"isSet\",\n" +
                "                \"params\":[]\n" +
                "                },\n" +
                "                {\n" +
                "                \"$$render_index\":2,\n" +
                "                \"$$searchValue\":\"内容\",\n" +
                "                \"field\":\"event.$Anything.$utm_content\",\n" +
                "                \"function\":\"isSet\",\n" +
                "                \"params\":[]\n" +
                "\t\t\t\t}]},\n" +
                "\"measures\": [           \n" +
                "\t\t\t{\n" +
                "            \"aggregator\":\"unique\",\n" +
                "            \"editName\":\"付费数\",\n" +
                "            \"event_name\":\"rechargeArrival_fixutm\",\n" +
                "            \"name\":\"付费数\"},\n" +
                "            {\n" +
                "            \"aggregator\":\"SUM\",\n" +
                "            \"editName\":\"充值额\",\n" +
                "            \"event_name\":\"rechargeArrival_fixutm\",\n" +
                "            \"field\": \"event.rechargeArrival_fixutm.amount\",\n" +
                "            \"name\":\"充值额\"} ,\n" +
                "            {\n" +
                "            \"aggregator\":\"uniqAvg\",\n" +
                "            \"editName\":\"人均金额ARPU\",\n" +
                "            \"event_name\":\"rechargeArrival_fixutm\",\n" +
                "            \"field\": \"event.rechargeArrival_fixutm.amount\",\n" +
                "            \"name\":\"人均金额ARPU\"} ,\n" +
                "            {\n" +
                "            \"editName\":\"当日LTV\",\n" +
                "            \"events\":[\"rechargeArrival_fixutm\", \"AppInstall\"],\n" +
                "            \"expression\":\"sum(event.rechargeArrival_fixutm.amount)/uniqcount(event.AppInstall)|%2f\",\n" +
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
                "\"use_cache\": \"false\",\n" +
                "\"valueTypes\":\"number\"}";

        req = req.replaceAll("\\$\\$from_date",fromDate).replaceAll("\\$\\$to_date",toDate).replaceAll("\\$\\$profiles_date",profilesDate);

        String result = HttpClientUtil.sendPost(url, req);
        String[] resStrs = result.split("\n");
        ArrayList<String> arr2 = new ArrayList(){};

        for (String res2 : resStrs) {
            if (res2.contains("合计")) {
                arr2.add(res2);
            }
        }

        for (HashMap.Entry<String, String> entry : allmap.entrySet()) {
            String key =  entry.getKey();
            String value = entry.getValue();

            if(!result.contains(key)){
                allmap.put(key,value+","+0+","+0+","+0+","+0);
                continue;
            }

            for (String arrstr : arr2) {
                try{
                    String[] arrstrs = arrstr.split(",");
                    String key2 = arrstrs[3];

                    if((key2.equals(key))){
                        allmap.put(key,value+","+arrstrs[4]+","+arrstrs[5]+","+arrstrs[6]+","+arrstrs[7]);
                    }
//                if(value2 == null){
//                    allmap.put(key2,toDate+" 00:00:00"+","+arrstrs[1]+","+arrstrs[2]+","+arrstrs[3]+","+"0"+","+arrstrs[4]+","+arrstrs[5]+","+arrstrs[6]+","+arrstrs[7]);
//                }
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }
            }

        }

    }

    public void registrationsETL(String fromDate, String toDate, String profilesDate, HashMap<String, String> allmap){

        String req = "{\n" +
                "\"bookmarkid\": \"3682\",\n" +
                "\"bucket_params\": {},\n" +
                "\"by_fields\": [\"event.$Anything.$utm_source\", \"event.$Anything.$utm_campaign\", \"event.$Anything.$utm_content\"],\n" +
                "\"detail_and_rollup\": \"true\",\n" +
                "\"enable_detail_follow_rollup_by_values_rank\": \"true\",\n" +
                "\"fromDash\": {\"id\":431,\"type\":\"nolmal\"},\n" +
                "\"from_date\": \"$$from_date\",\n" +
                "\"include_today\": \"true\",\n" +
                "\"filter\":{\"conditions\":[{\n" +
                "                \"$$render_index\":1,\n" +
                "                \"$$searchValue\":\"新增\",\n" +
                "                \"field\":\"user.user_tag_laiyuan_zhuce@$$profiles_date 00:00:00\",\n" +
                "                \"function\":\"isSet\",\n" +
                "                \"params\":[]\n" +
                "                },\n" +
                "                {\n" +
                "                \"$$render_index\":2,\n" +
                "                \"$$searchValue\":\"内容\",\n" +
                "                \"field\":\"event.$Anything.$utm_content\",\n" +
                "                \"function\":\"isSet\",\n" +
                "                \"params\":[]\n" +
                "\t\t\t\t}]},\n" +
                "\"measures\": [           \n" +
                "\t\t\t{\n" +
                "            \"aggregator\":\"unique\",\n" +
                "            \"editName\":\"付费数\",\n" +
                "            \"event_name\":\"rechargeArrival_fixutm\",\n" +
                "            \"name\":\"付费数\"},\n" +
                "            {\n" +
                "            \"aggregator\":\"SUM\",\n" +
                "            \"editName\":\"充值额\",\n" +
                "            \"event_name\":\"rechargeArrival_fixutm\",\n" +
                "            \"field\": \"event.rechargeArrival_fixutm.amount\",\n" +
                "            \"name\":\"充值额\"} ,\n" +
                "            {\n" +
                "            \"aggregator\":\"uniqAvg\",\n" +
                "            \"editName\":\"人均金额ARPU\",\n" +
                "            \"event_name\":\"rechargeArrival_fixutm\",\n" +
                "            \"field\": \"event.rechargeArrival_fixutm.amount\",\n" +
                "            \"name\":\"人均金额ARPU\"} ,\n" +
                "            {\n" +
                "            \"editName\":\"当日LTV\",\n" +
                "            \"events\":[\"rechargeArrival_fixutm\", \"AppInstall\"],\n" +
                "            \"expression\":\"sum(event.rechargeArrival_fixutm.amount)/uniqcount(event.AppInstall)|%2f\",\n" +
                "            \"expression_denominator_without_group\": false,\n" +
                "            \"format\": \"%2f\",\n" +
                "            \"isUnSaved\": true,\n" +
                "            \"name\":\"当日LTV\"} \n" +
                "\t\t],\n" +
                "\"request_id\": \"1614237606877:496674\",\n" +
                "\"server_time_zone\": \"\",\n" +
                "\"sub_task_type\": \"SEGMENTATION\",\n" +
                "\"time_zone_mode\": \"\",\n" +
                "\"to_date\": \"$$to_date\",\n" +
                "\"unit\": \"day\",\n" +
                "\"use_cache\": \"false\",\n" +
                "\"valueTypes\":\"number\"}";

        req = req.replaceAll("\\$\\$from_date",fromDate).replaceAll("\\$\\$to_date",toDate).replaceAll("\\$\\$profiles_date",profilesDate);

        String result = HttpClientUtil.sendPost(url, req);
        String[] resStrs = result.split("\n");
        ArrayList<String> arr2 = new ArrayList(){};

        for (String res2 : resStrs) {
            if (res2.contains("合计")) {
                arr2.add(res2);
            }
        }

        for (HashMap.Entry<String, String> entry : allmap.entrySet()) {
            String key =  entry.getKey();
            String value = entry.getValue();

            if(!result.contains(key)){
                allmap.put(key,value+","+0+","+0+","+0+","+0);
                continue;
            }

            for (String arrstr : arr2) {
                try{
                    String[] arrstrs = arrstr.split(",");
                    String key2 = arrstrs[3];

                    if((key2.equals(key))){
                        allmap.put(key,value+","+arrstrs[4]+","+arrstrs[5]+","+arrstrs[6]+","+arrstrs[7]);
                    }
//                if(value2 == null){
//                    allmap.put(key2,toDate+" 00:00:00"+","+arrstrs[1]+","+arrstrs[2]+","+arrstrs[3]+","+"0"+","+arrstrs[4]+","+arrstrs[5]+","+arrstrs[6]+","+arrstrs[7]);
//                }
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }
            }

        }
    }

    public void backflowsETL(String fromDate, String toDate, String profilesDate, HashMap<String, String> allmap){

        String req = "{\n" +
                "\"bookmarkid\": \"3683\",\n" +
                "\"bucket_params\": {},\n" +
                "\"by_fields\": [\"event.$Anything.$utm_source\", \"event.$Anything.$utm_campaign\", \"event.$Anything.$utm_content\"],\n" +
                "\"detail_and_rollup\": \"true\",\n" +
                "\"enable_detail_follow_rollup_by_values_rank\": \"true\",\n" +
                "\"fromDash\": {\"id\":431,\"type\":\"nolmal\"},\n" +
                "\"from_date\": \"$$from_date\",\n" +
                "\"include_today\": \"true\",\n" +
                "\"filter\":{\"conditions\":[{\n" +
                "                \"$$render_index\":1,\n" +
                "                \"$$searchValue\":\"新增\",\n" +
                "                \"field\":\"user.user_tag_laiyuan_huiliu@$$profiles_date 00:00:00\",\n" +
                "                \"function\":\"isSet\",\n" +
                "                \"params\":[]\n" +
                "                },\n" +
                "                {\n" +
                "                \"$$render_index\":2,\n" +
                "                \"$$searchValue\":\"内容\",\n" +
                "                \"field\":\"event.$Anything.$utm_content\",\n" +
                "                \"function\":\"isSet\",\n" +
                "                \"params\":[]\n" +
                "\t\t\t\t}]},\n" +
                "\"measures\": [           \n" +
                "\t\t\t{\n" +
                "            \"aggregator\":\"unique\",\n" +
                "            \"editName\":\"付费数\",\n" +
                "            \"event_name\":\"rechargeArrival_fixutm\",\n" +
                "            \"name\":\"付费数\"},\n" +
                "            {\n" +
                "            \"aggregator\":\"SUM\",\n" +
                "            \"editName\":\"充值额\",\n" +
                "            \"event_name\":\"rechargeArrival_fixutm\",\n" +
                "            \"field\": \"event.rechargeArrival_fixutm.amount\",\n" +
                "            \"name\":\"充值额\"} ,\n" +
                "            {\n" +
                "            \"aggregator\":\"uniqAvg\",\n" +
                "            \"editName\":\"人均金额ARPU\",\n" +
                "            \"event_name\":\"rechargeArrival_fixutm\",\n" +
                "            \"field\": \"event.rechargeArrival_fixutm.amount\",\n" +
                "            \"name\":\"人均金额ARPU\"} ,\n" +
                "            {\n" +
                "            \"editName\":\"当日LTV\",\n" +
                "            \"events\":[\"rechargeArrival_fixutm\", \"AppInstall\"],\n" +
                "            \"expression\":\"sum(event.rechargeArrival_fixutm.amount)/uniqcount(event.AppInstall)|%2f\",\n" +
                "            \"expression_denominator_without_group\": false,\n" +
                "            \"format\": \"%2f\",\n" +
                "            \"isUnSaved\": true,\n" +
                "            \"name\":\"当日LTV\"} \n" +
                "\t\t],\n" +
                "\"request_id\": \"1614237606877:496674\",\n" +
                "\"server_time_zone\": \"\",\n" +
                "\"sub_task_type\": \"SEGMENTATION\",\n" +
                "\"time_zone_mode\": \"\",\n" +
                "\"to_date\": \"$$to_date\",\n" +
                "\"unit\": \"day\",\n" +
                "\"use_cache\": \"false\",\n" +
                "\"valueTypes\":\"number\"}";

        req = req.replaceAll("\\$\\$from_date",fromDate).replaceAll("\\$\\$to_date",toDate).replaceAll("\\$\\$profiles_date",profilesDate);

        String result = HttpClientUtil.sendPost(url, req);
        String[] resStrs = result.split("\n");
        ArrayList<String> arr2 = new ArrayList(){};

        for (String res2 : resStrs) {
            if (res2.contains("合计")) {
                arr2.add(res2);
            }
        }

        for (HashMap.Entry<String, String> entry : allmap.entrySet()) {
            String key =  entry.getKey();
            String value = entry.getValue();

            if(!result.contains(key)){
                allmap.put(key,value+","+0+","+0+","+0+","+0);
                continue;
            }

            for (String arrstr : arr2) {
                try{
                    String[] arrstrs = arrstr.split(",");
                    String key2 = arrstrs[3];

                    if((key2.equals(key))){
                        allmap.put(key,value+","+arrstrs[4]+","+arrstrs[5]+","+arrstrs[6]+","+arrstrs[7]);
                    }
//                if(value2 == null){
//                    allmap.put(key2,toDate+" 00:00:00"+","+arrstrs[1]+","+arrstrs[2]+","+arrstrs[3]+","+"0"+","+arrstrs[4]+","+arrstrs[5]+","+arrstrs[6]+","+arrstrs[7]);
//                }
                }catch (Exception e){
                    e.printStackTrace();
                    continue;
                }
            }

        }

    }
//    public static void main(String[] args) {
//        weilianETL();
//    }

}
