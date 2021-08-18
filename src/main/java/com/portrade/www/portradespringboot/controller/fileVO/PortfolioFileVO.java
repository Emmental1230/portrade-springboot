package com.portrade.www.portradespringboot.controller.fileVO;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PortfolioFileVO {
    private String userId;
    private String title;
    private String content;
    private List<MultipartFile> files;
}
