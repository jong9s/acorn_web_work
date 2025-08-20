package com.example.spring05;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestController {
	
	@GetMapping("/javascript")
	public String javascript(Model model) {
		//로그인여부
		model.addAttribute("isLogin", false);
		//나이
		model.addAttribute("age", 25);
		//이름
		model.addAttribute("name", "서종훈");
		
		//회원 한명의 정보 
		MemberDto dto=MemberDto.builder().num(1).name("서종훈").addr("구리시").build();
		// 해당 데이터를 Model 객체에 담고 
		model.addAttribute("dto", dto);
		
		//DB 에서 select 한 결과라고 가정하자 
		MemberDto dto1=MemberDto.builder().num(1).name("종구").addr("구리구리시").build();
		MemberDto dto2=MemberDto.builder().num(2).name("해골").addr("행신동").build();
		MemberDto dto3=MemberDto.builder().num(3).name("원숭이").addr("상도동").build();
		
		// read only List 
		List<MemberDto> list=List.of(dto1, dto2, dto3);
		
		//Model 객체에 "list" 라는 키값으로 담기
		model.addAttribute("list", list);
		
		return "javascript";
	}
	
	@GetMapping("/unescape")
	public String unescape(Model model) {
		// html 형식의 문자열을 template 페이지에 전달할 일도 있다.
		String content = """
					<ul>
						<li>하나</li>
						<li>두울</li>
						<li>세엣</li>
						<li>뛰어</li>
					</ul>
				""";
		model.addAttribute("content", content);
		
		return "unescape";
	}
	
	@GetMapping("/print-num")
	public String printNum(Model model) {
		// 테스트를 위한 데이터 전달
		model.addAttribute("start", 6);
		model.addAttribute("end", 10);
		
		return "print-num";
	}
	
	@GetMapping("/include-test")
	public String includeTest(Model model) {
		// 테스트를 위한 데이터 전달
		model.addAttribute("title", "오늘의 운세");
		model.addAttribute("content", "동쪽으로 가면 귀인을 만나요");
		
		return "include-test";
	}
	
	/*
	 *  컨트롤러의 메소드에 UserDto 를 선언하면 폼 전송되는 파라미터가 자동으로 추출되어서
	 *  UserDto 의 필드에 담긴채로 전달된다.
	 *  
	 *  @ModelAttribute 어노테이션을 이용하면 view page 에서 해당 객체에 담긴 값을 활용할 수 있다.
	 */
	@PostMapping("/save")
	public String save(@ModelAttribute("dto") UserDto dto) {
		
		
		return "save";
	}
	
	@GetMapping("/form")
	public String form() {
		
		
		
		return "form";
	}
	
	@GetMapping("/if")
	public String ifTest(Model model) {
		
		model.addAttribute("score", 75);
		model.addAttribute("age", 25);
		
		return "if";
	}
	
	@GetMapping("/member/list")
	public String memberList(Model model) {
		// DB 에서 불러온 회원 목록이라고 가정
		MemberDto dto1 = MemberDto.builder().num(1).name("서종훈").addr("구리시").build();
		MemberDto dto2 = MemberDto.builder().num(2).name("종구").addr("구리시2").build();
		MemberDto dto3 = MemberDto.builder().num(3).name("종가").addr("구리시3").build();
		List<MemberDto> list = List.of(dto1, dto2, dto3);
		// 응답에 필요한 데이터를 Model 객체에 담는다.
		model.addAttribute("list", list);
		
		return "member/list";
	}
	
	
	@GetMapping("/member/detail")
	public String memberDetail(Model model) {
		// DB 에서 불러온 회원 한명의 정보라고 가정하자
		MemberDto dto = MemberDto.builder().num(1).name("서종훈").addr("구리시").build();
		// 응답에 필요한 정보를 Model 객체에 담는다
		model.addAttribute("dto", dto);
		// /templates/member/detail.html 
		return "member/detail";
	}
}
