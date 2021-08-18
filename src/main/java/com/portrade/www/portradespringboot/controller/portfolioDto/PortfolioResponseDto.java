package com.portrade.www.portradespringboot.controller.portfolioDto;

import com.portrade.www.portradespringboot.domain.Portfolio;
import lombok.Getter;

import java.util.List;

@Getter
public class PortfolioResponseDto {

    private Long id;
    private String user;
    private String title;
    private String content;
    private String portfolioType;
    private List<Long> fileId;

    public PortfolioResponseDto(Portfolio entity, List<Long> fileId) {
        this.id = entity.getId();
        this.user = entity.getUser().getEmail();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.portfolioType = entity.getPortfolioType();
        this.fileId = fileId;
    }

}
