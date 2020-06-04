package com.example.demo.config.interceptor;

import com.example.demo.AuditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuditInterceptor extends HandlerInterceptorAdapter {

    private AuditService auditService;

    @Autowired
    public AuditInterceptor(AuditService auditService) {
        this.auditService = auditService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals(HttpMethod.GET.name()) &&
                DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())) {
            auditRequest(request);
        }
        return super.preHandle(request, response, handler);
    }

    private void auditRequest(HttpServletRequest request) throws JsonProcessingException {
        Object payload = request.getParameterMap();
        auditService.auditRequest(request, payload);
    }
}
