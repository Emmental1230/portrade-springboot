package com.portrade.www.portradespringboot.controller.photoDto;

import com.portrade.www.portradespringboot.domain.Photo;
import lombok.Getter;

@Getter
public class PhotoResponseDto {

    private Long photoId;

    public PhotoResponseDto(Photo entity) {
        this.photoId = entity.getId();
    }

}
