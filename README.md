# hello-spring
스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술

* 생성자가 하나일 때는 @Autowired 생략 가능하다

# 19 강 JDBC
MemberService -> MemberRepository -> MemoryMemberRepository
로 주입받고 있던 것들이 

SpringConfiguration에서 JdbcMemberRepository를 주입 받게 해줬더니 코드를 갈아 끼울 수 있었음.

MemberService -> MemberRepository -> JdbcMemberRepository
로 한방에 구현 코드를 바꿀 수 있었다. DI를 왜 해줘야하나 했더니 인터페이스의 구현 코드가 담긴 클래스를 의존 관계를 바꾸는 것만으로 구조를 변경 할 수 있다는게 너무 신기하다.
이것이 가능했던 이유는 SOLID 중 개방-폐쇄 원칙을 따랐기 때문이라고 한다.

* 개방-폐쇄 원칙(OCP, Open-Closed Principle) : 개체(클래스, 모듈, 함수 등등)는 확장에 대해 열려 있어야 하고, 수정에 대해서는 닫혀 있어야 한다'는 프로그래밍 원칙이다.

개방에는 열려 있으나 수정에는 닫혀 있어야 한다는 말은 리포지토리를 갈아끼운것 같은 번경(확장)은 자유롭게 이뤄지되, 변경이 이뤄지면서 의존하는 코드들을 변경하는 행위는 닫혀 있어야한다는 뜻으로 이해했다.


# 20 강 스프링 통합 테스트
@SpringBootTest : 스프링 컨테이너와 테스트를 함께 실행한다.
@Transactional : 테스트 시 insert 한 DB의 내용들을 테스트 종료 후 롤백 시켜준다. 테스트 시작전에 트랜잭션을 실행하고, 완료후에는 롤백하기 때문이다.
@Commit : 선언된 메서드의 트랜잭션을 반드시 수행 완료 시킨다.

DB까지 연동하는걸 보통 통합 테스트라고 하는데, 가급적이면 스프링 컨테이너 없는 순수한 단위 테스트가 훨씬 좋은 테스트일 확률이 높다고 하시더라.


# 21 강 JPA
- JPA는 기존의 반복 코드는 물론이고, 기본적인 SQL도 JPA가 직접 만들어서 실행해준다.
- JPA를 사용하면, SQL과 데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환을 할 수 있다.
- JPA를 사용하면 개발 생산성을 높일 수 있다



# 24 강 AOP 필요
시간을 측정해야하는 로직이 포함된 예제가 있었다.
join 메서드에 System.currentTimeMillis() 로 시간을 측정해서 출력까지 하는 예제였는데 이것을 공통 관심 사항이라고 하더라.
회원가입 기능은 핵심 관심 사항(core concern), 시간 측정 기능은 공통 관심 사항(cross-cutting-concern)으로 이 두가지가 섞이면서 발생하는 문제는 아래와 같다.

- 시간을 측정하는 로직과 핵심 비즈니스의 로직이 섞여서 유지보수가 어렵다.
- 시간을 측정하는 로직을 별도의 공통 로직으로 만들기 매우 어렵다.
- 시간을 측정하는 로직을 변경할 때 모든 로직을 찾아가면서 변경해야 한다.

# 25 강 AOP 적용

TimeTraceAop 라는 클래스를 만들고
@Around("execution(* hello.hellospring.service..*(..))")로 서비스 패키지의 메서드 전체에 joint 시켜 시간 측정 기능을 구현했다.
- AOP 적용 전, 스프링 컨테이너에서 컨트롤러 서비스 의존 관계
  helloController ---> memberService
- AOP 적용 후, 컨트롤러 서비스 의존 관계
  helloController ---> memberService(프록시) ---> memberService(실제)

AOP 가 적용이 되어 있다면 가짜 memberService를 만들어서 호출을 하고, joinPoint.proceed()를 만났을 때, 실제 memberService를 호출한다
