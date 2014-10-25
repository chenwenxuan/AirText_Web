/*
 * Copyright (c) 2014 Qunar.com. All Rights Reserved.
 */
package me.airtext.utils;

/**
 * Created by xuan on 14-10-25.
 */
public class HTMLUtils {
    public static String stringToHTML(String originString){
        String resultString = originString.replace("\n","<br/>").replace(" ","&nbsp;").replace("\t","&nbsp;&nbsp;");
        return resultString;
    }
}
