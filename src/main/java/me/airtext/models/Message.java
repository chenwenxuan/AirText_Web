/*
 * Copyright (c) 2014 Qunar.com. All Rights Reserved.
 */
package me.airtext.models;

import java.util.Date;

/**
 * Created by xuan on 14-10-23.
 */
public class Message {
    private Integer id;
    private String secret;
    private String message;
    private Date createTime;
    private String sourceIp;

    public Message() {
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
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }
}
