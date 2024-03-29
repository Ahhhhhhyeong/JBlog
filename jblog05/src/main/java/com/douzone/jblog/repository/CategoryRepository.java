package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;
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

	public List<CategoryVo> getfindAll(String id) {
		return sqlSession.selectList("category.findAll", id);
	}

	public List<CategoryVo> getfindCountAll(String id) {
		return sqlSession.selectList("category.findCountAll", id);
	}

	public void insert(CategoryVo vo) {
		sqlSession.insert("category.insert", vo);
	}

	public void delete(String id, Long no) {		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("no", no);
		sqlSession.delete("category.delete", map);
	}

	public long getfindCount(Long no) {
		return sqlSession.selectOne("category.findCount", no);
	}

	public long findCountCategory(String id) {
		return sqlSession.selectOne("category.findCountCategory", id);
	}

}
