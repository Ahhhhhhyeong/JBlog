package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.exception.FileUploadException;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private FileUploadService fileUploadService;
	@Autowired
	private PostService postService;

	@RequestMapping({ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String index(@PathVariable("id") String id, @PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2, Model model) {

		Long categoryNo = 0L;
		Long postNo = 0L;

		if (pathNo2.isPresent()) { // 객체 존재여부 확인
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		
		BlogVo blogVo = blogService.getfindAll(id);
		
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("categoryVo", categoryService.getfindAll(id));
		model.addAttribute("postList", postService.getfindAll(categoryNo, id));
		model.addAttribute("postVo", postService.getfindOne(categoryNo, postNo, id));
		model.addAttribute("blog", blogVo.getTitle());

		return "/blog/main";
	}

	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id, @AuthUser UserVo authUser, Model model) {

		if(authUser == null) {
			return "redirect:/";
		}
		
		if (!authUser.getId().equals(id)) {
			return "redirect:/";
		}
			

		BlogVo blogVo = blogService.getfindAll(id);

		model.addAttribute("blogVo", blogVo);
		model.addAttribute("blog", blogService.getBlog(id));

		return "/blog/admin/basic";
	}

	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String adminBasic(@PathVariable("id") String id, @AuthUser UserVo authUser,
			@RequestParam("logo-file") MultipartFile multipartFile, BlogVo vo) throws FileUploadException {

		if(authUser == null) {
			return "redirect:/";
		}
		
		if (!authUser.getId().equals(id)) {
			return "redirect:/";
		}

		String url = fileUploadService.restoreImage(multipartFile);
		vo.setLogo(url);
		vo.setId(authUser.getId());
		blogService.updateBasic(vo);

		return "redirect:/" + id;
	}

	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id, @AuthUser UserVo authUser, Model model) {

		if(authUser == null) {
			return "redirect:/";
		}
		
		if (!authUser.getId().equals(id)) {
			return "redirect:/";
		}
		List<CategoryVo> list = categoryService.getfindCountAll(id);
		model.addAttribute("list", list);
		model.addAttribute("blog", blogService.getBlog(id));
		return "/blog/admin/category";
	}

	@RequestMapping(value = "/admin/category", method = RequestMethod.POST)
	public String adminCategory(@PathVariable("id") String id, @AuthUser UserVo authUser, CategoryVo vo) {

		if(authUser == null) {
			return "redirect:/";
		}
		
		if (!authUser.getId().equals(id)) {
			return "redirect:/";
		}
		vo.setBlog_id(id);
		categoryService.insert(vo);

		return "redirect:/" + id + "/category";
	}

	@RequestMapping(value = "/admin/category/delete/{no}", method = RequestMethod.GET)
	public String adminCategoryDelete(@PathVariable("id") String id, @AuthUser UserVo authUser,
			@PathVariable("no") Optional<Long> pathNo) {

		if(authUser == null) {
			return "redirect:/";
		}
		
		if (!authUser.getId().equals(id)) {
			return "redirect:/";
		}
		Long no = 0L;

		if (pathNo.isPresent()) { // 객체 존재여부 확인
			no = pathNo.get();
		}

		categoryService.delete(id, no);

		return "redirect:/" + id + "/category";
	}

	@RequestMapping("/admin/write")
	public String adminWrite(@PathVariable("id") String id, @AuthUser UserVo authUser, Model model) {

		if(authUser == null) {
			return "redirect:/";
		}
		
		if (!authUser.getId().equals(id)) {
			return "redirect:/";
		}

		List<CategoryVo> list = categoryService.getfindAll(id);

		model.addAttribute("list", list);
		model.addAttribute("blog", blogService.getBlog(id));
		return "/blog/admin/write";
	}

	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String adminWrite(@PathVariable("id") String id, @AuthUser UserVo authUser, PostVo vo) {

		if(authUser == null) {
			return "redirect:/";
		}
		
		if (!authUser.getId().equals(id)) {
			return "redirect:/";
		}

		postService.insert(vo);

		return "redirect:/" + id;
	}

}
