package com.portrade.www.portradespringboot.domain.inquiry;

import lombok.AllArgsConstructor;

import static com.google.common.base.Preconditions.checkArgument;

@AllArgsConstructor
public enum InquiryTypeEnum {

    PORTFOLIO("portfolio","포트폴리오"),
    USER("user","회원 관련"),
    ENTERPRISE("enterprise","기업 관련"),
    ERROR("error","기능/작동 오류"),
    ETC("etc","기타 문의");

    private final String code;
    private final String value;

    public String code() {
        return code;
    }
    public String value() {
        return value;
    }

    public static InquiryTypeEnum OfCode(final String descCode) throws Exception {
        checkArgument(descCode != null, "descCode must be not null");

        final String input = descCode.toLowerCase();

        if (input.equals(PORTFOLIO.code())) {
            return PORTFOLIO;
        } else if (input.equals(USER.code())) {
            return USER;
        } else if (input.equals(ENTERPRISE.code())) {
            return ENTERPRISE;
        } else if (input.equals(ERROR.code())) {
            return ERROR;
        } else if (input.equals(ETC.code())) {
            return ETC;
        } else {
            throw new Exception("NOT_FOUND_EXCEPTION");
        }
    }
}