package com.felix.project.commodity.service;

import com.felix.project.commodity.model.Type;


public interface TypeService {
    String addServiceType(Type type);

    String delServiceType(int typeId);

    String updateServiceType(Type type);

    String getAllServiceType();
}
