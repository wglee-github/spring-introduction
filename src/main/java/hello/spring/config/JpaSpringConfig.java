package hello.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import hello.spring.repository.MemberRepository;
import hello.spring.repository.impl.JpaMemberRepository;
import hello.spring.service.MemberService;
import jakarta.persistence.EntityManager;

//@Configuration
public class JpaSpringConfig {

	private EntityManager em;

	/**
	 *  @Autowired 를 선언하면 스프링이 JPA의 EntityManager를 아래 생성자에 주입해 준다.
	 */
	@Autowired
	public JpaSpringConfig(EntityManager em) {
		this.em = em;
	}

	/**
	 * MemberService에 MemberRepository(JpaMemberRepository)를 주입 한 후 MemberService를 빈에 등록한다.
	 */
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	/**
	 * EntityManager를 JpaMemberRepository에 주입 후 JpaMemberRepository를 빈에 등록한다.
	 */
	@Bean
	public MemberRepository  memberRepository() {
		return new JpaMemberRepository(em);
	}
	
}
