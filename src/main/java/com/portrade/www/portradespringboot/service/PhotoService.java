package com.portrade.www.portradespringboot.service;

import com.portrade.www.portradespringboot.controller.photoDto.PhotoDto;
import com.portrade.www.portradespringboot.controller.photoDto.PhotoResponseDto;
import com.portrade.www.portradespringboot.domain.Photo;
import com.portrade.www.portradespringboot.domain.Portfolio;
import com.portrade.www.portradespringboot.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PhotoService {

    private final PhotoRepository photoRepository;

    /**
     * 이미지 개별 조회
     */
    @Transactional(readOnly = true)
    public PhotoDto findByFileId(Long id){

        Photo entity = photoRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 파일이 존재하지 않습니다."));

        PhotoDto photoDto = PhotoDto.builder()
                .origFileName(entity.getOriginName())
                .filePath(entity.getFilePath())
                .fileSize(entity.getFileSize())
                .build();

        return photoDto;
    }

    /**
     * 이미지 전체 조회
     */
    @Transactional(readOnly = true)
    public List<PhotoResponseDto> findAllByBoard(Long boardId){

        List<Photo> photoList = photoRepository.findAllByPortfolioId(boardId);

        return photoList.stream()
                .map(PhotoResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Photo photo = photoRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 포트폴리오가 존재하지 않습니다."));

        photoRepository.delete(photo);
    }

}
