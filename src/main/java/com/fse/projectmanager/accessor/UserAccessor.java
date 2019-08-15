package com.fse.projectmanager.accessor;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fse.projectmanager.entity.User;

@Component("userAccessor")
public class UserAccessor {

	private static final Logger LOGGER = LogManager.getLogger(UserAccessor.class);
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> getUsers() {
		List<User> userList = null;
		try {
			userList = userRepository.findAll();
		} catch(NoSuchElementException e) {
			return new ArrayList<User>();
		} catch (Exception e) {
			LOGGER.error(e);
		}
		
		return userList;
	}
	
	public User getUser(Integer userId) {
		User user = null;
		try {
			user = userRepository.findById(userId).get();
		} catch(NoSuchElementException e) {
			return null;
		} catch (Exception e) {
			LOGGER.error(e);
		}
		
		return user;
	}
	
	public boolean saveUser(User user) {
		boolean status = false;
		try {
			if(user != null) {
				userRepository.save(user);
				status = true;
			}
		} catch (Exception e) {
			LOGGER.error(e);
			status = false;
		}
		
		return status;
	}
	
	public boolean deleteUser(Integer userId) {
		boolean status = false;
		try {
			if(userId != null && userId > 0) {
				userRepository.deleteById(userId);
				status = true;
			}
		} catch (Exception e) {
			LOGGER.error(e);
			status = false;
		}
		
		return status;
	}
	
}
