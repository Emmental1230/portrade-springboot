package com.portrade.www.portradespringboot.service;

import com.portrade.www.portradespringboot.domain.inquiry.Inquiry;
import com.portrade.www.portradespringboot.domain.inquiry.InquiryType;
import com.portrade.www.portradespringboot.repository.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryRepository inquiryRepository;
    private final InquiryTypeService inquiryTypeService;

    /**
     *     private String category;
     *     private String name;
     *     private String phoneNumber;
     *     private String title;
     *     private String contents;
     */
    @Transactional
    public Inquiry saveInquiry(final String categoryCode, final String name, final String phoneNumber, final String email, final String title, final String contents) throws Exception {
        checkArgument(categoryCode != null, "categoryCode must be not null");
        checkArgument(name != null, "name must be not null");
        checkArgument(phoneNumber != null, "phoneNumber must be not null");
        checkArgument(email != null, "email must be not null");
        checkArgument(title != null, "title must be not null");
        checkArgument(contents != null, "contents must be not null");
        checkArgument(title.length() <= 20, "contents must be not null");

        //TODO
        /**
         * 핸드폰 번호 정규식 걸기
         * 이메일 정규식 걸기
         * Contents 최대 길이 제한 걸기
         */

        InquiryType category = inquiryTypeService.findByCode(categoryCode);

        Inquiry inquiry = Inquiry.builder()
                .category(category)
                .name(name)
                .phoneNumber(phoneNumber)
                .email(email)
                .title(title)
                .contents(contents)
                .build();

        return inquiryRepository.save(inquiry);
    }
}
