package com.felix.project.user.mapper;

import com.felix.project.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getUserByUsername(String username);

    int modifyPassword(@Param("username") String username, @Param("password") String password);

    List<User> getUserList();

    int getUserCount();

    int getUserCountOfToday(String birth);

}