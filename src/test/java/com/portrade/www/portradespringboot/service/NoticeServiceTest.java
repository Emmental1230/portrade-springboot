package com.portrade.www.portradespringboot.service;

import com.portrade.www.portradespringboot.domain.notice.Notice;
import com.portrade.www.portradespringboot.repository.NoticeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NoticeServiceTest {

    @InjectMocks
    private NoticeService noticeService;

    @Mock
    private NoticeRepository noticeRepository;

    String title, contents;

    @BeforeEach
    public void init() {
        title = "제목";
        contents = "내용";
    }

    @Test
    public void save_테스트() {
        //given
        Notice notice = Notice.builder().title(title).contents(contents).build();
        given(noticeRepository.save(any())).willReturn(notice);

        //when
        Notice savedNotice = noticeService.save(title, contents);

        //then
        verify(noticeRepository).save(any());
        assertThat(title, is(savedNotice.getTitle()));
        assertThat(contents, is(savedNotice.getContents()));
    }

    @Test
    public void findById_테스트() throws Exception {
        //given
        Long mockId = 1L;
        Notice notice = Notice.builder().id(mockId).title(title).contents(contents).build();

        given(noticeRepository.findById(mockId)).willReturn(Optional.of(notice));

        //when
        Notice result = noticeService.findById(mockId);

        //then
        verify(noticeRepository).findById(mockId);
        assertThat(result.getId(), is(notice.getId()));
        assertThat(result.getTitle(), is(notice.getTitle()));
        assertThat(result.getContents(), is(notice.getContents()));
    }
}