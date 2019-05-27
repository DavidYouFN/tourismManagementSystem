package com.felix.project.commodity.service.impl;

import com.felix.project.commodity.mapper.StrategyMapper;
import com.felix.project.commodity.model.Strategy;
import com.felix.project.commodity.service.StrategyService;
import com.felix.project.commonConfig.util.JsonUtil;
import com.felix.project.commonConfig.util.StaticProperties;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        strategy.setState("0");
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
}
