package jpabook.jpashop;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class MemberRepository {
    // PersistenceContext 어노테이션을 달아야 em 객체를 주입 받을 수 있다.
    @PersistenceContext
    private EntityManager em;

    // 멤버 객체를 인자로 받아 멤버 테이블에 값을 저장 하도록 하기
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    // memberId로 멤버 테이블 조회 하기
    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}

