package hello.spring.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import hello.spring.domain.Member;

public class MemoryMemberRepository implements MemberRepository {

	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0;
	
	public Member save(Member member) {
		member.setId(++sequence);
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		return Optional.ofNullable(store.get(id));
	}

	@Override
	public Optional<Member> findByName(String name) {
		return store.values().stream()
				.filter(m -> m.getName().equals(name))
				.findAny();
	}

	@Override
	public List<Member> findAll() {
		return new ArrayList<>(store.values());
	}

	/**
	 * 메모리 비우기
	 * null이 되는게 아님. 그냥 값을 비움 -> {}
	 */
	public void clearStore() {
//		store.values().stream().forEach(m -> System.out.println("before.member = " + m));
		store.clear();
		System.out.println("store.clear");
//		store.values().stream().forEach(m -> System.out.println("after.member = " + m));
	}
	
}
