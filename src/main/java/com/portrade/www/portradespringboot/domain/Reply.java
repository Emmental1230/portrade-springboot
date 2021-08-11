package com.portrade.www.portradespringboot.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table

public class Reply extends BaseTimeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

//    @Column
//    private User user();

//    @Builder
//    public Reply(String content, User user){
//        this.content = content;
//        this.user = user;
//    }

}
