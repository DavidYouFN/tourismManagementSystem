package com.felix.project.user.service;

import com.alipay.api.AlipayApiException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserPropertyService {
    String addProperty(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String userId, String property) throws IOException, AlipayApiException;

    String getWalletInfo(String userId);
}
