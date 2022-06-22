package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;

	public List<PostVo> getfindBlogId(String id) {
		return sqlSession.selectList("post.getfindBlogId", id);
	}

	public List<PostVo> getfindCategoryNo(Long categoryNo) {
		return sqlSession.selectList("post.getfindCategoryNo", categoryNo);
	}

	public PostVo getfindBlogIdOne(String id) {
		return sqlSession.selectOne("post.getfindBlogIdOne", id);
	}

	public PostVo getfindOne(Long categoryNo, Long postNo, String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cno", categoryNo);
		map.put("pno", postNo);
		map.put("id", id);
		
		return sqlSession.selectOne("post.getfindOne", map);
	}

	public void insert(PostVo vo) {
		sqlSession.insert("post.insert", vo);
	}



}
