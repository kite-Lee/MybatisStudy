package com.lee.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class JsonUtils {

    // 仅传入一个参数  时间格式 默认为 "yyyy-MM-dd HH:mm:ss"
    public static String getJson(Object object) {
        return getJson(object, "yyyy-MM-dd HH:mm:ss");
    }


    public static String getJson(Object object, String dateFormat) {
        ObjectMapper mapper = new ObjectMapper();

        // 不使用时间戳的方式
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        // 自定义日期的格式
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        mapper.setDateFormat(simpleDateFormat);

        // return
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
