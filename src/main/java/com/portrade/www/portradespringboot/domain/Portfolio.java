package com.portrade.www.portradespringboot.domain;

import java.io.Serializable;
import javax.persistence.*;

import com.portrade.www.portradespringboot.domain.enums.PortfolioType;
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

    @Column
    private String title;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private PortfolioType portfolioType;

}
