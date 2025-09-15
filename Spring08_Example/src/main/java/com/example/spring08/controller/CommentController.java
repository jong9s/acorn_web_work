package com.example.spring08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring08.dto.CommentDto;
import com.example.spring08.service.CommentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommentController {
	
	private final CommentService commentService;
	
	@PostMapping("/{category}/comment-update")
	public String commentUpdate(CommentDto dto, @PathVariable(name = "category") String category) {
		
		commentService.updateComment(dto);
		
		return "redirect:/" + category + "/view?num="+dto.getParentNum();
	}
	
	@GetMapping("/{category}/comment-delete")
	public String commentDelete(CommentDto dto, @PathVariable(name = "category") String category) {
		// dto 에는 삭제할 댓글의 글 번호와 원 글의 글 번호가 들어있다.
		commentService.deleteComment(dto.getNum());
		
		return "redirect:/" + category + "/view?num="+dto.getParentNum();
	}
	
	@PostMapping("/{category}/save-comment")
	public String commentSave(CommentDto dto, @PathVariable(name = "category") String category) {
		// 서비스를 이용해서 새로운 댓글을 저장한다
		commentService.createComment(dto);
		// 댓글을 작성한 원 글 자세히 보기로 다시 리다이렉트 이동시키기
		return "redirect:/" + category + "/view?num="+dto.getParentNum();
	}
}
