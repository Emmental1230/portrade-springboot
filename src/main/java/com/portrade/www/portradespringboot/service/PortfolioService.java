package com.portrade.www.portradespringboot.service;

import com.portrade.www.portradespringboot.controller.portfolioDto.PortfolioCreateRequestDto;
import com.portrade.www.portradespringboot.controller.portfolioDto.PortfolioListResponseDto;
import com.portrade.www.portradespringboot.controller.portfolioDto.PortfolioResponseDto;
import com.portrade.www.portradespringboot.controller.portfolioDto.PortfolioUpdateRequestDto;
import com.portrade.www.portradespringboot.domain.Photo;
import com.portrade.www.portradespringboot.domain.Portfolio;
import com.portrade.www.portradespringboot.repository.PhotoRepository;
import com.portrade.www.portradespringboot.repository.PortfolioRepository;
import com.portrade.www.portradespringboot.service.Handler.PhotoHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final PhotoRepository photoRepository;
    private final PhotoHandler photoHandler;

    @Transactional
    public Long create(@RequestBody PortfolioCreateRequestDto requestDto, List<MultipartFile> files) throws Exception {

        Portfolio portfolio = new Portfolio(requestDto.getUser()
                , requestDto.getTitle()
                , requestDto.getContent()
                , requestDto.getPortfolioType());

        List<Photo> photoList = photoHandler.parseFileInfo(portfolio, files);

        if(!photoList.isEmpty()) {
            for(Photo photo : photoList)
                portfolio.addPhoto(photoRepository.save(photo));
        }

        return portfolioRepository.save(portfolio).getId();
    }

    @Transactional
    public Long update(Long id, PortfolioUpdateRequestDto requestDto, List<MultipartFile> files) throws Exception {
        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 포트폴리오가 존재하지 않습니다."));

        List<Photo> photoList = photoHandler.parseFileInfo(portfolio, files);

        if(!photoList.isEmpty()) {
            for(Photo photo : photoList) {
                photoRepository.save(photo);
            }
        }

        portfolio.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getPortfolioType());

        return id;
    }

    @Transactional(readOnly = true)
    public PortfolioResponseDto searchById(Long id, List<Long> fileId) {
        Portfolio portfolio = portfolioRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 포트폴리오가 존재하지 않습니다."));

        return new PortfolioResponseDto(portfolio, fileId);
    }

    @Transactional(readOnly = true)
    public List<Portfolio> searchAllDesc() {
        return portfolioRepository.findAllByOrderByIdDesc();
    }

    @Transactional
    public void delete(Long id){
        Portfolio portfolio = portfolioRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 포트폴리오가 존재하지 않습니다."));

        portfolioRepository.delete(portfolio);
    }

}
