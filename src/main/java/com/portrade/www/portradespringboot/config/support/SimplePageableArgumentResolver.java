package com.portrade.www.portradespringboot.config.support;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
@Component
public class SimplePageableArgumentResolver implements HandlerMethodArgumentResolver {

    private static final int DEFAULT_OFFSET = 0;

    private static final int DEFAULT_LIMIT = 10;

    private final String offsetParam;
    private final String limitParam;

    public SimplePageableArgumentResolver() {
        this("offset", "limit");
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return Pageable.class.isAssignableFrom(methodParameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory binderFactory) throws Exception {
        final String offsetString = nativeWebRequest.getParameter(offsetParam);
        final String limitString = nativeWebRequest.getParameter(limitParam);

        int offset = NumberUtils.toInt(offsetString, DEFAULT_OFFSET);
        int limit = NumberUtils.toInt(limitString, DEFAULT_LIMIT);

        if (offset < DEFAULT_OFFSET) {
            offset = DEFAULT_OFFSET;
        }
        if (limit < 1 || limit > DEFAULT_LIMIT) {
            limit = DEFAULT_LIMIT;
        }

        return new PageableImpl(offset, limit);
    }
}
