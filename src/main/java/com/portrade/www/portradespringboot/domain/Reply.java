package com.portrade.www.portradespringboot.domain;

import javax.persistence.*;
import com.portrade.www.portradespringboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table()
public class Reply extends BaseTimeEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn()
    private Portfolio portfolio;

    @OneToOne
    @JoinColumn()
    private User user;

    @Column(nullable = false)
    private String content;

    @Builder
    public Reply(String content, User user){
        this.content = content;
        this.user = user;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;

        if(!portfolio.getReply().contains(this))
            portfolio.getReply().add(this);
    }

}
