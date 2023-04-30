package hello.spring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.spring.domain.Member;
import hello.spring.repository.impl.MemoryMemberRepository;

class MemoryMemberRepositoryTest {

	MemoryMemberRepository memberRepository = new MemoryMemberRepository();
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
		System.out.println("afterEach");
	}
	
	@Test
	void save() {
		Member member = new Member();
		member.setName("spring");
		memberRepository.save(member);
		
		Member findMember = memberRepository.findById(member.getId()).get();
		
		assertThat(findMember).isEqualTo(member);
		
	}

	@Test
	void findByName() {
		Member member = new Member();
		member.setName("spring1");
		memberRepository.save(member);
		
		Member member1 = new Member();
		member1.setName("spring2");
		memberRepository.save(member1);
		
		Member findMember = memberRepository.findByName("spring1").orElse(null);
		System.out.println("findMember"+findMember);
		assertThat(findMember.getName()).isEqualTo("spring1");
		
	}
	
	@Test
	void findAll() {
		Member member = new Member();
		member.setName("spring1");
		memberRepository.save(member);
		
		Member member1 = new Member();
		member1.setName("spring2");
		memberRepository.save(member1);
		
		List<Member> members = memberRepository.findAll();
		
		assertThat(members.size()).isEqualTo(2);
	}

}
