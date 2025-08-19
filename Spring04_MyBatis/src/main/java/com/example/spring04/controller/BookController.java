package com.example.spring04.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.spring04.dto.BookDto;
import com.example.spring04.service.BookService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BookController {
	private final BookService service;
	
	@PostMapping("/book/update")
	public String update(BookDto dto) {
		service.updateBook(dto);
		
		return "book/update";
	}
	
	@GetMapping("/book/edit")
	public String edit(int num, Model model) {
		BookDto dto = service.getBook(num);
		model.addAttribute("dto", dto);
		
		return "book/edit";
	}
	
	@GetMapping("/book/delete")
	public String delete(int num) {
		service.deleteBook(num);
		
		return "book/delete";
	}
	
	@PostMapping("/book/insert")
	public String insert(BookDto dto) {
		service.addBook(dto);
		
		return "redirect:/book/list";
	}
	
	@GetMapping("/book/insert-form")
	public String insertform() {
		return "book/insert-form";
	}
	
	@GetMapping("/book/list")
	public String list(Model model) {
		List<BookDto> list = service.getAll();
		model.addAttribute("list", list);
		
		return "book/list";
	}
}
