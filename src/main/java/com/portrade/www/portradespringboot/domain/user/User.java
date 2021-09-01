package com.portrade.www.portradespringboot.domain.user;

import com.portrade.www.portradespringboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@NoArgsConstructor  //기본 생성자 자동 생성
@Entity     //DB 테이블과 1:1 매칭되는 객체 단위
// 사용자 정보를 담당할 도메인
public class User extends BaseTimeEntity {

    @Id //기본키값
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)    //문자열 자체가 칼럼의 값으로 사용
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
