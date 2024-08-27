package edu.sch.springboot.service;

import edu.sch.springboot.domain.Member;
import edu.sch.springboot.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {
    MemberService memberService;

    @Autowired
    public MemberServiceTest(MemberService memberService) {
        this.memberService = memberService;
    }

    @Test
    void join() {
        //1. 객체 생성
        Member member = new Member(); // 객체 생성
        member.setName("spring"); // 객체 이름 spring

        //2. store에 저장
        Long saveId = memberService.join(member);

        //3. store에 저장된 객체를 id를 통해 가져오기
        Member findMember = memberService.findMemberId(saveId).get();

        //4. 생성된 객체의 name과 store에서 가져온 객체의 name 비교
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void findMembers() {
    }
}