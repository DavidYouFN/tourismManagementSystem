package com.felix.project.user.service;

public interface AdminService {
    String getAdminInfo();

    String adminLogin(String adminName, String adminPassword);
}
