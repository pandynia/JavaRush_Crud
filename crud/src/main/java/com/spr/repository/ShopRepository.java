package com.spr.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.spr.model.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer> {
	
	
}
