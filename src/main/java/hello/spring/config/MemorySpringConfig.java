package hello.spring.config;

import org.springframework.context.annotation.Bean;

import hello.spring.repository.MemberRepository;
import hello.spring.repository.impl.MemoryMemberRepository;
import hello.spring.service.MemberService;

//@Configuration
public class MemorySpringConfig {

	/**
	 * MemberService에 MemberRepository(MemoryMemberRepository)를 주입 한 후 MemberService를 빈에 등록한다.
	 */
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	/**
	 * MemoryMemberRepository를 빈에 등록한다.
	 */
	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
}

