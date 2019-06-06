package com.felix.project.commodity.service.impl;

import com.felix.project.commodity.mapper.StrategyMapper;
import com.felix.project.commodity.model.Strategy;
import com.felix.project.commodity.service.StrategyService;
import com.felix.project.commonConfig.util.JsonUtil;
import com.felix.project.commonConfig.util.StaticProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName StrategyServiceImpl
 * @Description TODO
 * @Author fangyong
 * @Date 2019/5/26 21:12
 **/
@Service
public class StrategyServiceImpl implements StrategyService {

    @Resource
    StrategyMapper strategyMapper;

    @Override
    public String addStrategy(Strategy strategy) {
        String strategyDetail = strategy.getStrategy();
        String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(strategyDetail);
        strategyDetail = m_html.replaceAll(""); // 过滤html标签
        strategyDetail.trim();
        strategy.setState("0");
        strategy.setStrategy(strategyDetail);
        strategyMapper.insert(strategy);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    @Override
    public String getStrategy() {
        List<Strategy> strategyList = strategyMapper.getStrategy();
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,strategyList);
    }

    @Override
    public String checkStrategy(String userId) {
        strategyMapper.checkStrategy(userId);
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,"");
    }

    @Override
    public String getUserStrategyList() {
        List list = new ArrayList();
        List<Strategy> strategyList = strategyMapper.getUserStrategyList();
        for(Strategy strategy : strategyList){
            String strategyDetail = strategy.getStrategy();
            list.add(strategyDetail);
        }
        return new JsonUtil().JsonInfo(StaticProperties.RESPONSE_STATE_SUCCESS,StaticProperties.RESPONSE_MESSAGE_SUCCESS,list);
    }
}
