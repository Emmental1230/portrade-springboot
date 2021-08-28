package com.portrade.www.portradespringboot.service;

import com.portrade.www.portradespringboot.domain.notice.Notice;
import com.portrade.www.portradespringboot.error.NotFoundException;
import com.portrade.www.portradespringboot.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Notice findById(final Long id) {
        return noticeRepository.findById(id).orElseThrow(() -> new NotFoundException(Notice.class, id));
    }

    /**
     * page = offset
     * size = limit
     */
    public List<Notice> findAll(final int offset, final int limit) {
        return noticeRepository.findAll(PageRequest.of(offset, limit)).getContent();
    }

    @Transactional
    public Notice save(String title, String contents) {
        Notice faq = Notice.builder().title(title).contents(contents).build();
        return noticeRepository.save(faq);
    }
}