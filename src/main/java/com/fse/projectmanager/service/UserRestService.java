package com.fse.projectmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fse.projectmanager.accessor.UserAccessor;
import com.fse.projectmanager.entity.User;

@Controller
@RequestMapping("/user")
public class UserRestService {
	
	@Autowired
	private UserAccessor userAccessor;

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody User findUser(@PathVariable("userId") Integer userId) {
		return userAccessor.getUser(userId);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<User> findUsers() {
		return userAccessor.getUsers();
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, 
			consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<String> addUser(@RequestBody User user) {
		boolean status = userAccessor.saveUser(user);
		return new ResponseEntity<String>("{\"status\": "+ status +"}", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.POST,
			consumes = "application/json", produces = "application/json")
	public @ResponseBody ResponseEntity<String> deleteUser(@PathVariable("userId") Integer userId) {
		boolean status = userAccessor.deleteUser(userId);
		return new ResponseEntity<String>("{\"status\": "+ status +"}", HttpStatus.OK);
	}
}
