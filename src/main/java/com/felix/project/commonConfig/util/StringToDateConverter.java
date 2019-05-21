package com.felix.project.commonConfig.util;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * @Project:SupervisionSystem
 * @Description:string to date
 * @Author:TjSanshao
 * @Create:2019-02-18 21:12
 *
 **/
public class StringToDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = simpleDateFormat.parse(s);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
