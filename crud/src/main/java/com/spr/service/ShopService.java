package com.spr.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.spr.exception.ShopNotFound;
import com.spr.model.Shop;

public interface ShopService {
	
	public Shop create(Shop shop);
	public Shop delete(int id) throws ShopNotFound;
	public Page<Shop> findAll(Integer pageNumber);
	
	public Shop update(Shop shop) throws ShopNotFound;
	public Shop findById(int id);

}
