package hello.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import hello.spring.domain.Member;

/**
 *
 	· @SpringBootTest : 스프링 컨테이너와 테스트를 함께 실행한다.
	· @Transactional : 테스트 케이스에 이 애노테이션이 있으면, 테스트 시작 전에 트랜잭션을 시작하고, 
      테스트 완료 후에 항상 롤백한다. 이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다.
 *
 */
@SpringBootTest
@Transactional
class MemberServiceIntergrationTest {

	@Autowired MemberService memberService;
	
	@Test
//	@Commit
	void 회원가입() {
		//given
		Member member = new Member();
		member.setName("spring");
		
		//when
		Long saveId = memberService.join(member);
		
		//then
		Member findMember = memberService.findOne(saveId).orElseGet(null);
		assertThat(findMember.getName()).isEqualTo(member.getName());
	}

	@Test
	void 중복_회원_예외() {
		//given
		Member member1 = new Member();
		member1.setName("spring1");
		memberService.join(member1);
		
		
		//when
		Member member2 = new Member();
		member2.setName("spring1");
		
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
		
		//then
	}
	
//	@Test
	void testFindMembers() {
		//given
		
		//when
		
		//then
	}

//	@Test
	void testFindOne() {
		//given
		
		//when
		
		//then
	}

}
