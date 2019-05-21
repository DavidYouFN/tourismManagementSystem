package com.felix.project.user.mapper;

import com.felix.project.user.model.RolePermission;

public interface RolePermissionMapper {
    int insert(RolePermission record);

    int insertSelective(RolePermission record);
}