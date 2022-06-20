package com.douzone.jblog.repository;

import javax.validation.Valid;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(@Valid UserVo userVo) {
		return sqlSession.insert("user.insert", userVo) == 1;		
	}

	public UserVo getUser(String email, String password) {
		
		return null;
	}

}
