package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // 테스트 시 insert 한 DB에 있는 내용들을 롤백해준다
class MemberServiceIntegrationTest {
    // 테스트이기 때문에 필드 주입으로 한다
    @Autowired MemberRepository memberRepository;

    @Autowired MemberService memberService;

    @Test
    @Commit
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("spring2");
        // when
        Long saveId = memberService.join(member);
        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복회원예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");


        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); // 오른쪽 람다식을 실행할 것이고, 왼쪽 에러가 뜰것이다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");


        /* 위에 있는 코드랑 동일함.
        try {
            memberService.join(member2);
            fail(); // 무조건 실패하고 다음 라인 실행하지 않는다
        } catch(IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }
        */
        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}