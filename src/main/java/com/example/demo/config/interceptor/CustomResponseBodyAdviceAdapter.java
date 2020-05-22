package com.example.demo.config.interceptor;

import com.example.demo.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.UUID;

@ControllerAdvice
public class CustomResponseBodyAdviceAdapter implements ResponseBodyAdvice<Object> {

    @Autowired
    AuditService auditService;

    @Override
    public boolean supports(MethodParameter methodParameter,
                            Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        if (serverHttpRequest instanceof ServletServerHttpRequest &&
                serverHttpResponse instanceof ServletServerHttpResponse) {
            auditRequestResponse(o, (ServletServerHttpRequest) serverHttpRequest, (ServletServerHttpResponse) serverHttpResponse);
        }

        return o;
    }

    private void auditRequestResponse(Object responseBody,
                                      ServletServerHttpRequest serverHttpRequest,
                                      ServletServerHttpResponse serverHttpResponse) {

        UUID uuid = UUID.randomUUID();
        //auditService.auditRequest(uuid, serverHttpRequest.getServletRequest());
        auditService.auditResponse(uuid, serverHttpResponse.getServletResponse(), responseBody);
    }

}