package com.portrade.www.portradespringboot.controller.portfolioDto;

import com.portrade.www.portradespringboot.domain.Portfolio;
import lombok.Getter;

@Getter
public class PortfolioListResponseDto {

    private Long id;
    private String user;
    private String title;
    private Long thumbnailId;

    public PortfolioListResponseDto(Portfolio entity) {
        this.id = entity.getId();
        this.user = entity.getUser().getEmail();
        this.title = entity.getTitle();

        if(!entity.getPhoto().isEmpty())
            this.thumbnailId = entity.getPhoto().get(0).getId();
        else
            this.thumbnailId = 0L;
    }

}
