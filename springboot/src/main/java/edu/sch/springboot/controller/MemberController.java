package edu.sch.springboot.controller;

import org.springframework.ui.Model;
import edu.sch.springboot.domain.Member;
import edu.sch.springboot.dto.MemberDto;
import edu.sch.springboot.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class MemberController {
    MemberService memberService;

    // 생성자를 통한 서비스 객체 의존성 주입
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }

    //회원 가입 로직 처리
    @PostMapping("/members/signup")
    public String signupProc(MemberDto memberDto) { // name = 홍길동
        // 로직 처리
        Member member = new Member();
        member.setName(memberDto.getName());
        member.setAddress(memberDto.getAddress());

        memberService.join(member);

        return "redirect:/";
    }

    //회원가입 화면
    @GetMapping("/members/signup")
    public String signup() {
        return "members/signupForm";
    }


    @GetMapping("/") // http://localhost:8080/
    public String home() {
        return "home"; // resources/templates/home.html
    }


}
