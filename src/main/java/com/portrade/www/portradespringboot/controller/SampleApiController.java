package com.portrade.www.portradespringboot.controller;

import com.portrade.www.portradespringboot.controller.common.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sample")
public class SampleApiController {

    /**
     * ApiResult 사용 방법
     * 1st. method의 return value를 ApiResult로 감싸서 선언한다. ex. ApiResult<Long>
     * 2nd. return 문에서 결과값을 ApiResult.OK()로 감싸서 리턴한다. ex. ApiResult.OK(System.currentTimeMillis());
     * @return
     */
    @GetMapping
    public ApiResult<Long> func() {
        return ApiResult.OK(System.currentTimeMillis());
    }
}
