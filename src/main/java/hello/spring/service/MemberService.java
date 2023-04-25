package hello.spring.service;

import java.util.List;
import java.util.Optional;

import hello.spring.domain.Member;
import hello.spring.repository.MemberRepository;
import hello.spring.repository.MemoryMemberRepository;

/**
 * 
 * service 는 비지니스 적인 용어를 사용하여 메소드명을 작성한다.
 */
public class MemberService {

	private final MemberRepository memberRepository = new MemoryMemberRepository();
	
	/**
	 * 회원가입
	 */
	public Long join(Member member) {
		// 중복 X
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
		.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}
	
	/**
	 * 전체 회원 조회
	 */
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}
	
	/**
	 * 단건 회원 조회 
	 */
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
}
