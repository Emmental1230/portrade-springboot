package com.portrade.www.portradespringboot.controller.notice.dto;

import com.portrade.www.portradespringboot.domain.notice.Notice;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NoticeResponse {
    private Long id;
    private String title;
    private String contents;
    private String timeStamp;

    public NoticeResponse(Notice source) {
        id = source.getId();
        title = source.getTitle();
        contents = source.getContents();
        timeStamp = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(source.getCreatedDate());
    }
}
