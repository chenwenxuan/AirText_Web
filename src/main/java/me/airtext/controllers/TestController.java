/*
 * Copyright (c) 2014 Qunar.com. All Rights Reserved.
 */
package me.airtext.controllers;

import me.airtext.services.ISecretService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by xuan on 14-10-23.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Resource
    ISecretService secretService;

    @RequestMapping("/insertSecret/{secret}")
    @ResponseBody
    public String insertSecret(@PathVariable("secret") String secret){
        secretService.updateSecret(secret);
        return "插入已执行";
    }
}
