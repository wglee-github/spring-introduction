package hello.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import hello.spring.repository.MemberRepository;
import hello.spring.repository.impl.JdbcMemberRepository;
import hello.spring.service.MemberService;

//@Configuration
public class JdbcSpringConfig {

	// dataSource 주입받기 위해 필드 선언
	private DataSource dataSource;

	/**
	 * 
	 * @Autowired 를 선언하면 Spring에서 runtime 시 Datasource를 아래 생성자에 주입해 준다. - 생성자 주입 DI
	 */
	@Autowired
	public JdbcSpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	/**
	 *  MemberService에 MemberRepository(JdbcMemberRepository)를 주입 한 후 MemberService를 빈에 등록한다.
	 */
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}

	/**
	 *  JdbcMemberRepository에 dataSource를 주입 한 후 JdbcMemberRepository 빈에 등록한다.
	 */
	@Bean
	public MemberRepository memberRepository() {
		return new JdbcMemberRepository(dataSource);
	}
}

