package com.portrade.www.portradespringboot.controller.portfolioDto;

import com.portrade.www.portradespringboot.domain.Portfolio;
import com.portrade.www.portradespringboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PortfolioCreateRequestDto {

    private User user;
    private String title;
    private String content;
    private String portfolioType;

    @Builder
    public PortfolioCreateRequestDto(User user, String title, String content) {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public Portfolio toEntity() {
        return Portfolio.builder()
                .title(title)
                .content(content)
                .portfolioType(portfolioType)
                .build();
    }

}
