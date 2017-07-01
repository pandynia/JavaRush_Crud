package com.spr.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.spr.exception.UsersNotFound;
import com.spr.model.Users;

public interface UsersService {
	
	public Users create(Users users);
	public Users delete(int id) throws UsersNotFound;
	public List<Users> findAll();
	public Users update(Users users) throws UsersNotFound;
	public Users findById(int id);
	public List<Users> findByName(String name);	
	public Page<Users> getUsersPages(Integer pageNumber);

}
