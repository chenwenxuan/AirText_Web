/*
 * Copyright (c) 2014 Qunar.com. All Rights Reserved.
 */
package me.airtext.services;

/**
 * Created by xuan on 14-10-23.
 */
public interface ISecretService {
    void updateSecret(String secret);
    boolean secretExists(String secret);
}
