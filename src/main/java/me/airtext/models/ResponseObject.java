/*
 * Copyright (c) 2014 Qunar.com. All Rights Reserved.
 */
package me.airtext.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuan on 14-10-29.
 */
public class ResponseObject {
    Integer status;
    String errorMsg;
    String operationInfo;
    Map<String, Object> data;

    public ResponseObject() {
        data = new HashMap<String, Object>();
        data.put("data",new HashMap<String, Object>());
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getOperationInfo() {
        return operationInfo;
    }

    public void setOperationInfo(String operationInfo) {
        this.operationInfo = operationInfo;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setKeyAndValue(String key, Object value){
        Map<String, Object> realData = (Map) data.get("data");
        realData.put(key,value);
    }
}
