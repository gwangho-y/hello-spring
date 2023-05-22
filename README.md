# hello-spring
스프링 입문 - 코드로 배우는 스프링 부트, 웹 MVC, DB 접근 기술



# 19 강 JDBC
MemberService -> MemberRepository -> MemoryMemberRepository
로 주입받고 있던 것들이 

SpringConfiguration에서 JdbcMemberRepository를 주입 받게 해줬더니 코드를 갈아 끼울 수 있었음.

MemberService -> MemberRepository -> JdbcMemberRepository
로 한방에 구현 코드를 바꿀 수 있었다. DI를 왜 해줘야하나 했더니 인터페이스의 구현 코드가 담긴 클래스를 의존 관계를 바꾸는 것만으로 이렇게 간단하게 할 수 있다는게 너무 신기하다.