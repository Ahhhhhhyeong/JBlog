package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	public List<PostVo> getfindAll(Long categoryNo, String id) { // 게시판 목차 나타낼거
		if(categoryNo == 0) {
			return postRepository.getfindBlogId(id);
		}
		return postRepository.getfindCategoryNo(categoryNo);
	}
	
	
	public PostVo getfindOne(Long categoryNo, Long postNo, String id) {
		if(categoryNo == 0 && postNo == 0) {
			return postRepository.getfindBlogIdOne(id);
		}
		return postRepository.getfindOne(categoryNo, postNo, id);
	}

	public void insert(PostVo vo) {
		postRepository.insert(vo);
	}

	
}
