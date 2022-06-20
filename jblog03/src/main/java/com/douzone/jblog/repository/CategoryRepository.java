package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class CategoryRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public boolean setBasic(@Valid UserVo userVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "게시판");
		map.put("description", "게시판");
		map.put("blog_id", userVo.getId());	
		
		return sqlSession.insert("category.insertDefault", map) == 1;		
	}

}
