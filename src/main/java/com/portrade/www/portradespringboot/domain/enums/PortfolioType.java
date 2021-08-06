package com.portrade.www.portradespringboot.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PortfolioType {

    CS("컴퓨터"), DESIGN("디자인");
    private final String value;

}
