package com.portrade.www.portradespringboot.controller;

import com.portrade.www.portradespringboot.controller.photoDto.PhotoDto;
import com.portrade.www.portradespringboot.domain.Photo;
import com.portrade.www.portradespringboot.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RequiredArgsConstructor
@RestController
public class PhotoController {

    private final PhotoService photoService;

    @CrossOrigin
    @GetMapping(
            value = "/thumbnail/{id}",
            produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE}
    )
    public ResponseEntity<byte[]> getThumbnail(@PathVariable Long id) throws IOException {
        String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
        String path;

        if(id != 0) {
            PhotoDto photoDto = photoService.findByFileId(id);
            path = photoDto.getFilePath();
        }
        else {
            path = "images" + File.separator + "thumbnail" + File.separator + "thumbnail.png";
        }

        InputStream imageStream = new FileInputStream(absolutePath + path);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();

        return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(
            value = "/image/{id}",
            produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE}
    )
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) throws IOException {
        PhotoDto photoDto = photoService.findByFileId(id);
        String absolutePath
                = new File("").getAbsolutePath() + File.separator + File.separator;
        String path = photoDto.getFilePath();

        InputStream imageStream = new FileInputStream(absolutePath + path);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();

        return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
    }

}
