package com.aloneness.itoken.common.utils;

import com.aloneness.itoken.common.dto.BaseResult;

public class Fallback {

    public static String badGateway(){
        BaseResult result = BaseResult.fail(502, "无法从上游服务获取信息");
        try {
            return MapperUtils.obj2json(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
