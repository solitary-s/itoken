package com.aloneness.itoken.common.dto;

import lombok.Data;

@Data
public class RespBean<T> {

    private Integer code;
    private String message;
    private T data;

    public static <T> RespBean success(String message, T data){
        RespBean<T> respBean = new RespBean<>();
        respBean.setCode(200);
        respBean.setMessage(message);
        respBean.setData(data);
        return respBean;
    }

    public static RespBean fail(String message){
        RespBean respBean = new RespBean<>();
        respBean.setCode(400);
        respBean.setMessage(message);
        return respBean;
    }

    public static RespBean fail(Integer code, String message){
        RespBean respBean = new RespBean<>();
        respBean.setCode(code);
        respBean.setMessage(message);
        return respBean;
    }

}
