package hello.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.spring.aop.TimeTraceAop;
import hello.spring.repository.MemberRepository;
import hello.spring.service.MemberService;

@Configuration
public class SpringConfig {

	private final MemberRepository memberRepository;
	
	@Autowired
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}
	
	@Bean
	public TimeTraceAop timeTraceAop() {
		return new TimeTraceAop();
	}
}
