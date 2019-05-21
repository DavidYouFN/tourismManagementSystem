package com.felix.project.user.service;

import com.felix.project.user.model.Seller;

public interface SellerService {

    String getSellerList();

    String delSeller(String sellerId);

    String modifySeller(Seller seller);
}
