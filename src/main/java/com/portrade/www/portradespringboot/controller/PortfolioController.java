package com.portrade.www.portradespringboot.controller;

import com.portrade.www.portradespringboot.config.auth.CustomOAuth2UserService;
import com.portrade.www.portradespringboot.controller.fileVO.PortfolioFileVO;
import com.portrade.www.portradespringboot.controller.photoDto.PhotoDto;
import com.portrade.www.portradespringboot.controller.photoDto.PhotoResponseDto;
import com.portrade.www.portradespringboot.controller.portfolioDto.PortfolioCreateRequestDto;
import com.portrade.www.portradespringboot.controller.portfolioDto.PortfolioListResponseDto;
import com.portrade.www.portradespringboot.controller.portfolioDto.PortfolioResponseDto;
import com.portrade.www.portradespringboot.controller.portfolioDto.PortfolioUpdateRequestDto;
import com.portrade.www.portradespringboot.domain.Photo;
import com.portrade.www.portradespringboot.domain.Portfolio;
import com.portrade.www.portradespringboot.domain.user.User;
import com.portrade.www.portradespringboot.service.PhotoService;
import com.portrade.www.portradespringboot.service.PortfolioService;
import com.portrade.www.portradespringboot.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final PhotoService photoService;
    private final PostsService postsService;
    private final CustomOAuth2UserService customOAuth2UserService;


//    @PostMapping("/portfolio")
//    @ResponseStatus(HttpStatus.CREATED)
//    public String create(@RequestBody Portfolio portfolioDto){
//        portfolioService.create(portfolioDto);
//        return "Success";
//    }

    @PostMapping("/portfolio")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(PortfolioFileVO portfolioFileVO) throws Exception {
//        User user = customOAuth2UserService.loadUser();

        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PortfolioCreateRequestDto requestDto = PortfolioCreateRequestDto.builder()
                    .user(user)
                    .title(portfolioFileVO.getTitle())
                    .content(portfolioFileVO.getContent())
                    .build();

        return portfolioService.create(requestDto, portfolioFileVO.getFiles());
    }

    @PutMapping("/portfolio/{id}")
    public Long update(@PathVariable Long id, PortfolioFileVO portfolioFileVO) throws Exception {

        PortfolioUpdateRequestDto requestDto =
                PortfolioUpdateRequestDto.builder()
                        .title(portfolioFileVO.getTitle())
                        .content(portfolioFileVO.getContent())
                        .build();

        List<PhotoResponseDto> dbPhotoList = photoService.findAllByBoard(id);

        List<MultipartFile> multipartFileList = portfolioFileVO.getFiles();

        List<MultipartFile> addFileList = new ArrayList<>();

        if(CollectionUtils.isEmpty(dbPhotoList)) {
            if (!CollectionUtils.isEmpty(multipartFileList)) {
                for (MultipartFile multipartFile : multipartFileList)
                    addFileList.add(multipartFile);
            }
        }
        else {
            if(CollectionUtils.isEmpty(multipartFileList)) {
                for(PhotoResponseDto dbPhoto : dbPhotoList)
                    photoService.delete(dbPhoto.getPhotoId());
            }
            else {
                List<String> dbOrginNameList = new ArrayList<>();

                for (PhotoResponseDto dbPhoto : dbPhotoList) {
                    PhotoDto dbPhotoDto = photoService.findByFileId(dbPhoto.getPhotoId());
                    String dbOriginFileName = dbPhotoDto.getOrigFileName();

                    if (!multipartFileList.contains(dbOriginFileName))
                        photoService.delete(dbPhoto.getPhotoId());
                    else
                        dbOrginNameList.add(dbOriginFileName);
                }
                for(MultipartFile multipartFile : multipartFileList) {
                    String multipartOriginName = multipartFile.getOriginalFilename();
                    if(!dbOrginNameList.contains(multipartOriginName)){
                        addFileList.add(multipartFile);
                    }
                }
            }
        }

        return portfolioService.update(id, requestDto, addFileList);
    }

    // 개별 조회
    @GetMapping("/portfolio/{id}")
    public PortfolioResponseDto searchById(@PathVariable Long id) {

        List<PhotoResponseDto> photoResponseDtoList = photoService.findAllByBoard(id);
        List<Long> photoId = new ArrayList<>();

        for(PhotoResponseDto photoResponseDto : photoResponseDtoList)
            photoId.add(photoResponseDto.getPhotoId());

        return portfolioService.searchById(id, photoId);
    }

    // 전체 조회 (목록)
    @GetMapping("/board")
    public List<PortfolioListResponseDto> searchAllDesc() {

        List<Portfolio> portfolioList = portfolioService.searchAllDesc();

        List<PortfolioListResponseDto> responseDtoList = new ArrayList<>();

        for(Portfolio portfolio : portfolioList) {
            PortfolioListResponseDto responseDto = new PortfolioListResponseDto(portfolio);
            responseDtoList.add(responseDto);
        }

        return responseDtoList;
    }

    @DeleteMapping("/portfolio/{id}")
    public void delete(@PathVariable Long id) {
        portfolioService.delete(id);
    }

}
