package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class BlogRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public boolean setBasic(@Valid UserVo userVo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", userVo.getId());
		map.put("title", userVo.getName() + "의 블로그");
		map.put("url", "/assets/gallery/default.jpg");		
		
		return sqlSession.insert("blog.insertDefault", map) == 1;		
	}

}
