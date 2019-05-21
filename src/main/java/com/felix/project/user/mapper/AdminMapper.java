package com.felix.project.user.mapper;

import com.felix.project.user.model.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    int deleteByPrimaryKey(String adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(String adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin adminLogin(@Param("adminName") String adminName, @Param("adminPassword") String adminPassword);
}