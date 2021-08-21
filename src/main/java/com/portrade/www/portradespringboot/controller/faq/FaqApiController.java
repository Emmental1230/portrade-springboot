package com.portrade.www.portradespringboot.controller.faq;

import com.portrade.www.portradespringboot.config.support.Pageable;
import com.portrade.www.portradespringboot.controller.faq.dto.FaqResponse;
import com.portrade.www.portradespringboot.domain.faq.Faq;
import com.portrade.www.portradespringboot.service.FaqService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/faqs")
public class FaqApiController {

    private final FaqService faqService;

    @GetMapping
    public Result<FaqResponse> getFaqs(Pageable pageable) {
        final List<FaqResponse> faqs = faqService.findAll(pageable.offset(), pageable.limit())
                .stream().map(FaqResponse::new).collect(Collectors.toList());
        return new Result(faqs.size(), faqs);
    }

    @GetMapping("{id}")
    public FaqResponse getFaq(@PathVariable("id") Long faqId) throws Exception {
        final Faq faq = faqService.findById(faqId);
        return new FaqResponse(faq);
    }

    @Data
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    static class Result<T> {
        private int count;
        private T data;
    }
}
