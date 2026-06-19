package com.springAi.LamNexus.util;

public class ResponseCleaner {

    public static String clean(String text){
        if(text==null){
            return "";
        }
        return text
                .replaceAll(
                        "(?s)<(think|thinking|reasoning|analysis)>.*?</\\1>",
                        ""
                )
                .trim();
    }
}
