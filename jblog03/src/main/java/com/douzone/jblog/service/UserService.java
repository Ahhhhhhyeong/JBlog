package com.douzone.jblog.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserVo getUser(String email, String password) {		
		return userRepository.getUser(email, password);
	}

	public void join(@Valid UserVo userVo) {
		userRepository.insert(userVo);
	}

}
