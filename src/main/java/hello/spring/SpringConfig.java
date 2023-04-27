package hello.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.spring.repository.JdbcMemberRepository;
import hello.spring.repository.JdbcTemplateMemberRepository;
import hello.spring.repository.JpaMemberRepository;
import hello.spring.repository.MemberRepository;
import hello.spring.repository.MemoryMemberRepository;
import hello.spring.service.MemberService;
import jakarta.persistence.EntityManager;

@Configuration
public class SpringConfig {

//	private DataSource dataSource;
//	@Autowired
//	public SpringConfig(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
	private EntityManager em;

	@Autowired
	public SpringConfig(EntityManager entityManager) {
		this.em = entityManager;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
//		return new MemoryMemberRepository();
//		return new JdbcMemberRepository(dataSource);
//		return new JdbcTemplateMemberRepository(dataSource);
		return new JpaMemberRepository(em);
	}
}
