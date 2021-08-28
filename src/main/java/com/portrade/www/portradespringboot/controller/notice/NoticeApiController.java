package com.portrade.www.portradespringboot.controller.notice;

import com.portrade.www.portradespringboot.config.support.Pageable;
import com.portrade.www.portradespringboot.controller.common.ApiResult;
import com.portrade.www.portradespringboot.controller.notice.dto.NoticeResponse;
import com.portrade.www.portradespringboot.domain.notice.Notice;
import com.portrade.www.portradespringboot.service.NoticeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/notices")
public class NoticeApiController {

    private final NoticeService noticeService;

    @GetMapping
    public ApiResult<Result<NoticeResponse>> getNotices(Pageable pageable) {
        final List<NoticeResponse> faqs = noticeService.findAll(pageable.offset(), pageable.limit())
                .stream().map(NoticeResponse::new).collect(Collectors.toList());
        return ApiResult.OK(new Result(faqs.size(), faqs));
    }

    @GetMapping("{id}")
    public ApiResult<NoticeResponse> getNotice(@PathVariable("id") Long noticeId) {
        final Notice notice = noticeService.findById(noticeId);
        return ApiResult.OK(new NoticeResponse(notice));
    }

    @Data
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    static class Result<T> {
        private int count;
        private T data;
    }
}
