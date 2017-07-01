package com.spr.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spr.exception.ShopNotFound;
import com.spr.exception.UsersNotFound;
import com.spr.model.Users;
import com.spr.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {
	private static final int PAGE_SIZE = 5;
	
	@Resource
	private UsersRepository usersRepository;

	public Page<Users> getUsersPages(Integer pageNumber) {
		PageRequest request = new PageRequest(pageNumber - 1, PAGE_SIZE);
		return usersRepository.findAll(request);
	}
	
	@Override
	@Transactional
	public Users create(Users users) {
		// TODO Auto-generated method stub		
		Users createdUsers = users;
		return usersRepository.save(createdUsers);		
	}
	
	@Override
	@Transactional
	public Users findById(int id) {
		return usersRepository.findOne(id);
	}
	
	@Override
	public List<Users> findByName(String name) {
		// TODO Auto-generated method stub
		return usersRepository.findName(name);
	}


	@Override
	@Transactional(rollbackFor=UsersNotFound.class)	
	public Users delete(int id) throws UsersNotFound {
		Users deletedUsers = usersRepository.findOne(id);
		
		if (deletedUsers == null)
			throw new UsersNotFound();
		
		usersRepository.delete(deletedUsers);
		return deletedUsers;		
	}

	@Override
	@Transactional
	public List<Users> findAll() {
		return usersRepository.findAll();
	}

	@Override
	@Transactional(rollbackFor=ShopNotFound.class)
	public Users update(Users users) throws UsersNotFound {		
		Users updatedUsers = usersRepository.findOne(users.getId());
		
		if (updatedUsers == null)
			throw new UsersNotFound();
		
		updatedUsers.setName(users.getName());
		updatedUsers.setAge(users.getAge());
		updatedUsers.setAdmin(users.isAdmin());
		updatedUsers.setCreatedDate(users.getCreatedDate());
		return updatedUsers;
	}


}
