/*
 * Copyright (c) 2014 Qunar.com. All Rights Reserved.
 */
package me.airtext.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xuan on 14-10-23.
 */
public class Message {
    private Integer id;
    private String secret;
    private String message;
    private Date createTime;
    private String createTimeString;
    private String sourceIp;

    public Message() {
    }

    public Message(String secret, String message, String sourceIp) {
        this.secret = secret;
        this.message = message;
        this.sourceIp = sourceIp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timeString = formatter.format(createTime);
        this.createTimeString = timeString;
    }

    public String getCreateTimeString() {
        return createTimeString;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }
}
