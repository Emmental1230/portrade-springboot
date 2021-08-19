package com.portrade.www.portradespringboot.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "photo")
public class Photo extends BaseTimeEntity {

    @Id
    @Column(name = "photo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    @Column
    private String originName;

    @Column
    private String filePath;

    private Long fileSize;

    @Builder
    public Photo(String originName, String filePath, Long fileSize) {
        this.originName = originName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;

        if(!portfolio.getPhoto().contains(this))
            portfolio.getPhoto().add(this);
    }

}
