package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;


@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	public BlogVo getfindAll(String id) {
		return blogRepository.getfindAll(id);
	}

	public void updateBasic(BlogVo vo) {
		blogRepository.updateBasic(vo);
	}
	public BlogVo getBlog(String id) {
		return blogRepository.find(id);
	}
	
}
