package Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * # -*- coding:utf-8 -*- #
 * 作者:30671
 * 日期:2022年04月12日20时54分
 */
public class ResponseDataMap {
    private Map<String, Object> sendData;

    /**
     * 回显信息格式
     * status 状态
     * msg    信息
     * data   数据
     */

    public ResponseDataMap() {
        sendData = new HashMap<>();
    }

    public ResponseDataMap(Integer status, String msg, Object data) {
        sendData = new HashMap<>();
        this.SetStatus(status);
        this.SetMsg(msg);
        this.SetData(data);
    }

    public void SetStatus(Integer status) {
        sendData.put("status", status);
    }

    public void SetMsg(String msg) {
        sendData.put("msg", msg);
    }

    public void SetData(Object data) {
        sendData.put("data", data);
    }

    public void Put(String key, Object data) {
        sendData.put("data", data);
    }

    public String toJson(){
        return GsonUtils.mapToJsonString(sendData, GsonUtils.GsonSerializerFeature.WriteNullValue);
    }

    public String toJson(GsonUtils.GsonSerializerFeature Setting){
        return GsonUtils.mapToJsonString(sendData, Setting);
    }

    @Override
    public String toString() {
        return GsonUtils.mapToJsonString(sendData, GsonUtils.GsonSerializerFeature.WriteNullValue);
    }

}
