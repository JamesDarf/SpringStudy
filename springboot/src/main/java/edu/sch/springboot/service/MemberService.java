package edu.sch.springboot.service;

import edu.sch.springboot.domain.Member;
import edu.sch.springboot.repository.MemberRepository;
import edu.sch.springboot.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private MemberRepository memberDao = new MemoryMemberRepository(); // memberDao 자식인 클래스
    // MemberRepository -> interface

    @Autowired
    public MemberService(MemberRepository memberDao) {
        this.memberDao = memberDao;
    }

    /**
     * 회원 가입
     * */
    public Long join(Member member) { // controller가 호출함. why? controller가 view랑 연결되어 있어서
        duplicatedMemberCheck(member);

        memberDao.save(member);
        return member.getId();
    }

    //회원 name 중복 체크
    private void duplicatedMemberCheck(Member member) {
        memberDao.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() { // 회원 전체를 조회
        return memberDao.findAll();
    }


    /**
     * 회원 ID 조회
     **/
    public Optional<Member> findMemberId(Long memberId) { // id return.
        return memberDao.findById(memberId);
    }

}

