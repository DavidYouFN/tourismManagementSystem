package com.felix.project.user.mapper;

import com.felix.project.user.model.UserProperty;
import org.apache.ibatis.annotations.Param;

public interface UserPropertyMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserProperty record);

    int insertSelective(UserProperty record);

    UserProperty selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserProperty record);

    int updateByPrimaryKey(UserProperty record);

    int addProperty(@Param("userId") String userId, @Param("property") String property);

    int updateProperty(@Param("userId")String userId, @Param("property")String property);
}