package hello.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	/**
	 * 정적 컨텐츠
	 */
	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data", "hello!!");
		return "hello";
	}
	
	/**
	 * 템플릿 엔진
	 * 
	 * @RequestParam 에 required 옵션 주기 ( default : true )
	 * @RequestParam(value = "name", required = true)
	 * 
	 */
	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam("name") String name, Model model) {
		model.addAttribute("name", name);
		return "hello-template";
	}

	/**
	 * API - String
	 * 
	 	* @ResponseBody 를 사용 하면
			· viewResolver 대신에 HttpMessageConverter 가 동작하는데
			· 기본 문자처리는 StringHttpMessageConverter 가 동작하여
			· HTTP의 BODY에 문자 내용을 직접 반환해 준다.
	 */
	@ResponseBody
	@GetMapping("hello-string")
	public String helloString(@RequestParam("name") String name) {
		return "hello " + name;
	}

	/**
	 * API - JSON
	 * 
	 * @ResponseBody 를 사용 하면
		· viewResolver 대신에 HttpMessageConverter 가 동작
		· 기본 객체처리는 MappingJackson2HttpMessageConverter 동작하여
		· HTTP의 BODY에 JSON으로 변환된 내용을 직접 반환해 준다.
		
		· byte 처리 등등 기타 여러 HttpMessageConverter가 기본으로 등록되어 있음


	 * 참고
	 	·  클라이언트의 HTTP Accept 해더와 서버의 컨트롤러 반환 타입 정보 둘을 조합해서 HttpMessageConverter 가 선택된다.
	 * 
	 */
	@ResponseBody
	@GetMapping("hello-api")
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hello = new Hello();
		hello.setName(name);
		return hello;
	}
	
	/**
	 * 
	 * getter, setter 
	 * - java bean 표준 방식
	 * - 프로퍼티 접근 방식
	 */
	static class Hello{
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
