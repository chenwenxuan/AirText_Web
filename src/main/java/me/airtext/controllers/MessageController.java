/*
 * Copyright (c) 2014 Qunar.com. All Rights Reserved.
 */
package me.airtext.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xuan on 14-10-22.
 */
@Controller
@RequestMapping("/entry")
public class MessageController {
    Logger logger = LoggerFactory.getLogger(MessageController.class);

    @RequestMapping("/start-chat")
    public String voidChat(){
        return "redirect:/";
    }

    @RequestMapping(value = "/start-chat", method = RequestMethod.POST)
    public String startChat(@RequestParam(value = "secret",required = true) String secret) throws Exception{
        String redirectUrl = "redirect:/entry/chat/"+secret+"";
        return redirectUrl;
    }

    @RequestMapping(value = "/chat/{secret}")
    public ModelAndView enterChat(@PathVariable(value = "secret") String secret, ModelAndView targetModelAndView){
        targetModelAndView.setViewName("chatroom");
        targetModelAndView.addObject("secret",secret);
        return targetModelAndView;
    }

    @RequestMapping(value = "/chat")
    public String enterChatWithoutSecret(ModelAndView targetModelAndView){
        return "redirect:/";
    }
}
