package com.portrade.www.portradespringboot.controller.inquiry.dto;

import com.portrade.www.portradespringboot.domain.inquiry.Inquiry;

public class InquiryResponse {

    private String category;
    private String name;
    private String phoneNumber;
    private String email;
    private String title;
    private String contents;

    InquiryResponse(Inquiry source) {
        category = source.getCategory().getDesc();
        name = source.getName();
        phoneNumber = source.getPhoneNumber();
        email = source.getEmail();
        title = source.getTitle();
    }
}
