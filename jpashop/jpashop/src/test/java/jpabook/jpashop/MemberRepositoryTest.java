package jpabook.jpashop;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testMember() throws Exception {
        // 멤버 객체 생성
        Member member = new Member();
        member.setUsername("memberD");

        // 멤버 객체 저장
        Long saveId = memberRepository.save(member);
        System.out.println("saveId :::::::::::::::::::::::::::::::::::: " + saveId);

        // 멤버 객체 조회 (저장한후 반환 받은 아이디로 조회 즉 저장된 멤버 객체를 조회)
        Member findMember = memberRepository.find(saveId);

        // 저장한 멤버 객체와 다시 조회한 객체가 동일함을 확인 해보자
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
    }


}
