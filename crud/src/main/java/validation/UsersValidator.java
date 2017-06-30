package com.spr.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.spr.model.Users;

@Component
public class UsersValidator implements Validator{
	private final static String USERS_NAME = "name";

	@Override
	public boolean supports(Class<?> clazz) {
		return Users.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Users users = (Users) target;
		
		String usersName = users.getName();
		
		ValidationUtils.rejectIfEmpty(errors, "name", "users.name.empty");
		ValidationUtils.rejectIfEmpty(errors, USERS_NAME, "users.name.empty");
		
		if (usersName != null && usersName.length() < 1)
			errors.rejectValue(USERS_NAME, "users.name.lessThenOne");

	}
}
