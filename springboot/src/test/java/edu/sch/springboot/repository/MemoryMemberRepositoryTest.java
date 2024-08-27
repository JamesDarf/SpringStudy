
package edu.sch.springboot.repository;
import edu.sch.springboot.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;


public class MemoryMemberRepositoryTest {
    MemoryMemberRepository memberDao = new MemoryMemberRepository(); // Dao -> 객체 이름을 많이 사용함.

    @AfterEach
    public void afterEach() {
        memberDao.clearStore();
    }

    /**
     * save 메소드 테스트 케이스
     */
    @Test
    public void saveTest() {
        Member member = new Member();
        member.setName("spring");

        memberDao.save(member); // 실행 |

        Member result = memberDao.findById(member.getId()).get(); // {1}.get() -> member(1, spring)을 뜻함.
        //System.out.println("result : " + (result == member));

        // 두개의 데이터가 똑같은지 확인함. 두개 중에 하나 사용하면 된다. (하나는 주석처리함.)
//        org.junit.jupiter.api.Assertions.assertEquals(member, result);
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(result);
    }


    /** findByName 메소드 테스트 케이스 */
    @Test
    public void findByNameTest() {
        Member member1 = new Member();
        member1.setName("spring1");
        memberDao.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberDao.save(member2);

        Member result = memberDao.findByName("spring1").get(); // store 저장된 객체 spring1을 가져온다.
        // 저장이 잘 됐다면 result 와 spring1의 해당하는 member1과 같아야 한다.
        org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member1);
    }


    /** findAll 메소드 테스트 케이스 */
    @Test
    public void findAllTest() {
        Member member1 = new Member();
        member1.setName("spring1");
        memberDao.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        memberDao.save(member2);

        List<Member> result = memberDao.findAll();
        org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2); // list가 2개이기에 size 2
    }


}