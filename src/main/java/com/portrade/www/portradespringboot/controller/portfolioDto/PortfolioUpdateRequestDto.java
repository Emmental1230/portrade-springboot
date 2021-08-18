package com.portrade.www.portradespringboot.controller.portfolioDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PortfolioUpdateRequestDto {

    private String title;
    private String content;
    private String portfolioType;

    @Builder
    public PortfolioUpdateRequestDto(String title, String content, String portfolioType) {
        this.title = title;
        this.content = content;
        this.portfolioType = portfolioType;
    }

}
