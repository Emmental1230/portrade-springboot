package com.portrade.www.portradespringboot.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.portrade.www.portradespringboot.domain.enums.PortfolioType;
import com.portrade.www.portradespringboot.domain.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table

public class Portfolio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private PortfolioType portfolioType;

    @Column
    private Reply reply;

    @Builder
    public Portfolio(String title, String content, PortfolioType portfolioType, Reply reply){
        this.title = title;
        this.content = content;
        this.portfolioType = portfolioType;
        this.reply = reply;
    }

}
