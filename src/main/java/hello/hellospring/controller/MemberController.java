package hello.hellospring.controller;


import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;


    @Autowired // 스프링이 MemberController를 생성할 때, MemberService를 결합시켜준다.
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}
