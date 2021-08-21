package com.portrade.www.portradespringboot.service;

import com.portrade.www.portradespringboot.domain.inquiry.InquiryType;
import com.portrade.www.portradespringboot.domain.inquiry.InquiryTypeEnum;
import com.portrade.www.portradespringboot.repository.InquiryTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.google.common.base.Preconditions.checkArgument;

@Service
@RequiredArgsConstructor
public class InquiryTypeService {

    private final InquiryTypeRepository inquiryTypeRepository;

    public InquiryType findByCode(final String descCode) throws Exception {
        checkArgument(descCode != null, "descCode must be not null");
        InquiryTypeEnum inquiryTypeEnum = InquiryTypeEnum.OfCode(descCode);
        return inquiryTypeRepository.findByDesc(inquiryTypeEnum.code());
    }
}
