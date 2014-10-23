/*
 * Copyright (c) 2014 Qunar.com. All Rights Reserved.
 */
package me.airtext.services;

import me.airtext.models.Message;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * Created by xuan on 14-10-23.
 */
public interface IMessageService {
    List<Message> getSecretMessagesInRange(String secret, RowBounds rowBounds);
    void insertMessage(Message message);
}
