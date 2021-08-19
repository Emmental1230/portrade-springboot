package com.portrade.www.portradespringboot.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.portrade.www.portradespringboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "PORTFOLIO")
public class Portfolio extends BaseTimeEntity{

    @Id
    @Column(name = "portfolio_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = User.class)
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column
    private String portfolioType;

    @OneToMany(
            mappedBy = "portfolio",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Photo> photo = new ArrayList<>();

    @OneToMany(
            mappedBy = "portfolio",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<Reply> reply = new ArrayList<>();

    @Builder
    public Portfolio(User user, String title, String content, String portfolioType){
        this.user = user;
        this.title = title;
        this.content = content;
        this.portfolioType = portfolioType;
    }

    // 포트폴리오 사진 처리를 위한 메서드
    public void addPhoto(Photo photo) {
        this.photo.add(photo);

        // 포트폴리오 사진이 없을 경우
        if(photo.getPortfolio() != this)
            photo.setPortfolio(this);
    }

    // 포트폴리오 댓글 처리를 위한 메서드
    public void addReply(Reply reply) {
        this.reply.add(reply);

        // 포트폴리오 댓글이 없을 경우
        if(reply.getPortfolio() != this)
            reply.setPortfolio(this);
    }

    public void update(String title, String content, String portfolioType) {
        this.title = title;
        this.content = content;
        this.portfolioType = portfolioType;
    }

}
