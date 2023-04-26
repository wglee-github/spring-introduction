package hello.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.spring.domain.Member;
import hello.spring.domain.MemberForm;
import hello.spring.service.MemberService;

@Controller
public class MemberController {
	
	/**
	 * 
	  * 스프링 빈과 의존관계
		· 컴포넌트 스캔과 자동 의존관계 설정
			· @Component 애노테이션이 있으면 스프링 빈으로 자동 등록된다.
			· @Controller 컨트롤러가 스프링 빈으로 자동 등록된 이유도 컴포넌트 스캔 때문이다.
			
			· @Component 를 포함하는 다음 애노테이션도 스프링 빈으로 자동 등록된다.
				· @Controller
				· @Service
				· @Repository
		
		· 참고
			· 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다(유일하게 하나만 등록해서 공유한다) 
			· 따라서 같은 스프링 빈이면 모두 같은 인스턴스다. 설정으로 싱글톤이 아니게 설정할 수 있지만, 특별한 경우를 제외하면 대부분 싱글톤을 사용한다.
		
		· 자바 코드로 직접 스프링 빈 등록하기 
			· 임의의 SpringConfig class 를 생성해서 직접 bean으로 등록해 준다
	  
	 */
	private final MemberService memberService;

	/**
	 *  참고: 
	 · class를 스프링빈으로 등록하는 방식중에 XML로 설정하는 방식도 있지만 최근에는 잘 사용하지 않으므로 생략한다.
	
	 	* 참고
		 	· DI 종류 
			 	· 필드 주입 : 필드에 @Autowired 를 선언하는 방식
			 	· setter 주입 : setter 메소드 추가 후 메소드에 @Autowired 를 선언하는 방식
			 	· 생성자 주입 : 생성자 추가 후 메소드에 @Autowired 를 선언하는 방식
		 		
		 		** 참고 : 생성자 주입 방식의 경우 생성자가 하나인 경우에는 @Autowired 가 없어도 스프링이 알아서 주입해 준다.
		 		
		 		· 의존관계가 실행중에 동적으로 변하는 경우는 거의 없으므로 생성자 주입을 권장한다.

	 	* 참고
		 	· 실무에서는 주로 정형화된 컨트롤러, 서비스, 리포지토리 같은 코드는 컴포넌트 스캔을 사용한다. 
			· 그리고 정형화 되지 않거나, 상황에 따라 구현 클래스를 변경해야 하면 설정을 통해 스프링 빈으로 등록한다.

	 	** 주의 
	 		· @Autowired 를 통한 DI는 helloController , memberService 등과 같이 스프링이 관리하는 객체에서만 동작한다. 
	 		· 스프링 빈으로 등록하지 않고 내가 직접 생성한 객체에서는 동작하지 않는다 
	 */
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping("/members/new")
	public String createForm() {
		return "members/createMemberForm";
	}
	
	@PostMapping("/members/new")
	public String create(MemberForm form) {
		Member member = new Member();
		member.setName(form.getName());
		memberService.join(member);
		return "redirect:/";
	}
	
	@GetMapping("members")
	public String list(Model model) {
		List<Member> members = memberService.findMembers();
		model.addAttribute("members", members);
		return "members/memberList";
	}
}
