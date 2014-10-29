/*
 * Copyright (c) 2014 Qunar.com. All Rights Reserved.
 */
package me.airtext.controllers;

import me.airtext.models.Message;
import me.airtext.models.ResponseObject;
import me.airtext.services.IMessageService;
import me.airtext.services.ISecretService;
import me.airtext.utils.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by xuan on 14-10-22.
 */
@Controller
@RequestMapping("/airtext")
public class MessageController {
    Logger logger = LoggerFactory.getLogger(MessageController.class);
    @Resource
    private ISecretService secretService;
    @Resource
    private IMessageService messageService;

    @RequestMapping("/start-chat")
    public String voidChat(){
        return "redirect:/";
    }

    @RequestMapping("/create-secret")
    public String createSecret(@RequestParam(value = "secret",required = false) String secret){
        secretService.updateSecret(secret);
        return "redirect:/airtext/chat/"+secret;
    }

    @RequestMapping(value = "/start-chat", method = RequestMethod.POST)
    public String startChat(@RequestParam(value = "secret",required = true) String secret, ModelMap modelMap) throws Exception{
        logger.info("in /start-chat : secret = "+secret);
        if (StringUtils.isBlank(secret)){
            return "redirect:/";
        }

        if (secretService.secretExists(secret)){
            return "redirect:/airtext/chat/"+secret;
        }
        else {
            modelMap.addAttribute("secret",secret);
            return "startchat";
        }
    }

    @RequestMapping(value = "/chat/{secret}")
    public String enterChat(HttpServletRequest request, @PathVariable(value = "secret") String secret,@RequestParam(value = "begin",required = false) Integer begin,@RequestParam(value = "length",required = false) Integer length, ModelMap modelMap, HttpServletResponse httpServletResponse){
        if (secretService.secretExists(secret)) {
            Cookie cookie = CookieUtils.getCookieWithName(request, "secret");
            if (cookie == null){
                cookie = new Cookie("secret",secret);
                cookie.setPath("/airtext");
            }
            else {
                cookie.setValue(secret);
                cookie.setPath("/airtext");
            }
            httpServletResponse.addCookie(cookie);

            //添加一个时间戳，删除message的时候作为凭证
            Cookie cookieMillis = CookieUtils.getCookieWithName(request,"page_time");
            if (cookieMillis == null){
                Date date = new Date();
                long time = date.getTime();
                String millis = ""+time;
                cookieMillis = new Cookie("page_time",millis);
                cookieMillis.setPath("/airtext");
            }
            httpServletResponse.addCookie(cookieMillis);

            if (begin == null || begin < 0){
                begin = new Integer(0);
            }
            if (length == null || length < 0 || length > 100){
                length = new Integer(100);
            }

            List<Message> messageList = messageService.getSecretMessagesInRange(secret, new RowBounds(begin, length));
            modelMap.addAttribute("messages",messageList);
            return "chatroom";
        }
        else {
            return "startchat";
        }
    }

    @RequestMapping(value = "/chat")
    public String enterChatWithoutSecret(ModelAndView targetModelAndView){
        return "redirect:/";
    }

    @RequestMapping("/add-message")
    public String addMessage(HttpServletRequest httpServletRequest, @CookieValue("secret") String secret, @RequestParam(value = "message",required = true) String messageText){
        String ipString = httpServletRequest.getRemoteAddr();
        Message message = new Message(secret,messageText,ipString);
        messageService.insertMessage(message);
        return "redirect:/airtext/chat/"+secret;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ResponseObject deleteMessage(HttpServletRequest httpServletRequest, @CookieValue("page_time") String cookieMillis, @RequestParam("message_id") Integer messageId, @RequestParam("millis") String paramMillis){
        ResponseObject responseObject = new ResponseObject();
        messageService.deleteMessage(messageId);
        responseObject.setStatus(200);
        responseObject.setOperationInfo("删除成功");
        return responseObject;
    }
}
