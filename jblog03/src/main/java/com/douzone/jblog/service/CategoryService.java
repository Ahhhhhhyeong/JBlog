package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryVo> getfindAll(String id) {
		
		return categoryRepository.getfindAll(id);
	}

	public List<CategoryVo> getfindCountAll(String id) {
		return categoryRepository.getfindCountAll(id);
	}

	public void insert(CategoryVo vo) {
		categoryRepository.insert(vo);
	}

	public boolean delete(String id, Long no) {
		//삭제 조건 
		if(categoryRepository.getfindCount(no) > 0L) { //카테고리안에 게시글이 1개 이상 있을 때 삭제 못하게
			return false;
		}
		
		if(categoryRepository.findCountCategory(id) == 1L) { // 카테고리개수가 1개일 때, 삭제 못하게
			return false;
		}
		
		categoryRepository.delete(id, no);
		return true;
	}

}
