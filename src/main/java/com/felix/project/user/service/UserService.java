package com.felix.project.user.service;

import com.felix.project.user.model.Shop;
import com.felix.project.user.model.Seller;
import com.felix.project.user.model.User;



public interface UserService {
    String userRegister(User user);

    User getUserByUsername(String username);

    String modifyPassword(String username, String password);

    String addShop(Shop shop, Seller seller);

    String getUserList();
}
