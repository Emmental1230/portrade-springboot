package com.portrade.www.portradespringboot.service;

import com.portrade.www.portradespringboot.domain.faq.Faq;
import com.portrade.www.portradespringboot.repository.FaqRepository;
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
class FaqServiceTest {

    @InjectMocks
    private FaqService faqService;

    @Mock
    private FaqRepository faqRepository;

    String title, contents;

    @BeforeEach
    public void init() {
        title = "제목";
        contents = "내용";
    }

    @Test
    public void save_테스트() {
        //given
        Faq faq = Faq.builder().title(title).contents(contents).build();
        given(faqRepository.save(any())).willReturn(faq);

        //when
        Faq savedFaq = faqService.save(title, contents);

        //then
        verify(faqRepository).save(any());
        assertThat(title, is(savedFaq.getTitle()));
        assertThat(contents, is(savedFaq.getContents()));
    }

    @Test
    public void findById_테스트() throws Exception {
        //given
        Long mockId = 1L;
        Faq faq = Faq.builder().id(mockId).title(title).contents(contents).build();

        given(faqRepository.findById(mockId)).willReturn(Optional.of(faq));

        //when
        Faq result = faqService.findById(mockId);

        //then
        verify(faqRepository).findById(mockId);
        assertThat(result.getId(), is(faq.getId()));
        assertThat(result.getTitle(), is(faq.getTitle()));
        assertThat(result.getContents(), is(faq.getContents()));
    }
}