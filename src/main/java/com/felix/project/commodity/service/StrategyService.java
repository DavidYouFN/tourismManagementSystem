package com.felix.project.commodity.service;

import com.felix.project.commodity.model.Strategy;

public interface StrategyService {
    String addStrategy(Strategy strategy);

    String getStrategy();

    String checkStrategy(String userId);
}
