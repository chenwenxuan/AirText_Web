/*
 * Copyright (c) 2014 Qunar.com. All Rights Reserved.
 */
package me.airtext.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by xuan on 14-10-24.
 */
public class CookieUtils {
    public static Cookie getCookeiWithName(HttpServletRequest request, String cookieName){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie: cookies){
            if (cookie.getName().compareTo(cookieName) == 0){
                return cookie;
            }
        }
        return null;
    }
}
