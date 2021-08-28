package com.portrade.www.portradespringboot.service;

import com.portrade.www.portradespringboot.domain.faq.Faq;
import com.portrade.www.portradespringboot.domain.user.User;
import com.portrade.www.portradespringboot.error.NotFoundException;
import com.portrade.www.portradespringboot.repository.FaqRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;

    public Faq findById(final Long id) {
        return faqRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
    }

    /**
     * page = offset
     * size = limit
     */
    public List<Faq> findAll(final int offset, final int limit) {
        return faqRepository.findAll(PageRequest.of(offset, limit)).getContent();
    }

    @Transactional
    public Faq save(String title, String contents) {
        Faq faq = Faq.builder().title(title).contents(contents).build();
        return faqRepository.save(faq);
    }
}
