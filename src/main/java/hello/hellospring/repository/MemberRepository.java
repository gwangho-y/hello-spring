package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);  // Optional java8에서부터 들어가는 기능, 만약 null을 반환해야할 경우에 Optional로 감싸서 반환한다.
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
