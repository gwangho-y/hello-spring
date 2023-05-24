package hello.hellospring.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // 객체와 dadabase의 모델을 매핑해준다. JPA가 관리하는 모델이라고 선언하는 것.
public class Member {
    @Id // PK 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY) // id를 자동으로 생성해준다 IDENTITY 전략이라고 부름
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}
