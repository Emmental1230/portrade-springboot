package com.portrade.www.portradespringboot.controller.notice.dto;

import com.portrade.www.portradespringboot.domain.notice.Notice;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeResponse {
    private Long id;
    private String title;
    private String contents;

    public NoticeResponse(Notice source) {
        id = source.getId();
        title = source.getTitle();
        contents = source.getContents();
    }
}
