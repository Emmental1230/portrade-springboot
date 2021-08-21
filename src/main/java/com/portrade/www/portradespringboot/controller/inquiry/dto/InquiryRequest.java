package com.portrade.www.portradespringboot.controller.inquiry.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class InquiryRequest {

    private String category;
    private String name;
    private String phoneNumber;
    private String email;
    private String title;
    private String contents;
}
