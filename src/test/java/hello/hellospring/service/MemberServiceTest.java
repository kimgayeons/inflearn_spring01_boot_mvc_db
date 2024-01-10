package hello.hellospring.service;

import hello.hellospring.domain.Member;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/*alt + enter 클릭 후 test 자동 생성*/
class MemberServiceTest {

    //MemberService memberService = new MemberService();
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    /*d*/
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void join() {
        //given(이러한 상황이 주어지고, 이 데이터를 기반으로 하는구나)
        Member member = new Member();
        member.setName("spring");

        //when 실행했을때, 이걸 검증하구나
        Long saveId = memberService.join(member);

        //then 이런 결과가 나와야한다
        //머리 가슴 배 구분으로 나누기
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        /*테스트 메서드는 한글을 써도 상관없다*/
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
/*
        try{
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.2");
        }*/
        //assertThrows(IllegalStateException.class, ()->memberService.join(member2));
        //assertThrows(NullPointerException.class, ()->memberService.join(member2));
        //memberService.join(member2) 이 로직을 실행할꺼고, IllegalStateException.class 이예외가 발생해야한다

        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*Expected java.lang.IllegalStateException to be thrown, but nothing was thrown. : 예외가 나와야 하는데 예외가 나오지 않았을 경우 나
        * 나타나는 에러 코드*/
        //then

    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}