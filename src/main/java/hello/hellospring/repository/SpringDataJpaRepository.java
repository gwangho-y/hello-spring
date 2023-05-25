package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaRepository extends JpaRepository<Member, Long>, MemberRepository {

    // 기본적으로 JpaRepository 안에 findByName, findById 같은 기본적인 조회 기능 같은 것들은 저장이 되어 있다.
    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
