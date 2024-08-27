
package edu.sch.springboot.repository;

import edu.sch.springboot.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{
    /** Memory 저장 */
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // 아이디는 자동으로 넣는 상황

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // 사용할 때마다 하나씩 증가하는 형태로 데이터를 넣음 | id:1, name:spring
        store.put(member.getId(), member); // 1, member(1, spring) 데이터를 넣음
        return null;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // hashMap으로 리턴하면 사용하기 힘들어서 arraylist로 사용함.
    }


    public void clearStore() {
        store.clear();
    }
}
