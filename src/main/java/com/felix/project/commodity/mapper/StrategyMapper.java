package com.felix.project.commodity.mapper;

import com.felix.project.commodity.model.Strategy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StrategyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Strategy record);

    int insertSelective(Strategy record);

    Strategy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Strategy record);

    int updateByPrimaryKey(Strategy record);

    List<Strategy> getStrategy();

    int checkStrategy(@Param("userId") String userId);

    List<Strategy> getUserStrategyList();
}