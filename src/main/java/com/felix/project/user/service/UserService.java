package com.felix.project.user.service;

import com.felix.project.user.model.Shop;
import com.felix.project.user.model.Seller;
import com.felix.project.user.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


public interface UserService {
    String userRegister(User user);

    User getUserByUsername(String username);

    String modifyPassword(String username, String password);

    String addShop(Shop shop, Seller seller);

    String getUserList();

    String getUserInfoByUserId(String userId);

    String modifyUserInfo(User user);

    String getUserCount();

    String getSellerCount();

    String getUserCountOfToday();

    String getUserCountOfSevenDay(String item);
}
