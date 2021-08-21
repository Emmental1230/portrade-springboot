package com.portrade.www.portradespringboot.controller.faq.dto;

import com.portrade.www.portradespringboot.domain.faq.Faq;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FaqResponse {
    private Long id;
    private String title;
    private String contents;

    public FaqResponse(Faq source) {
        id = source.getId();
        title = source.getTitle();
        contents = source.getContents();
    }
}
