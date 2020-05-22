package com.example.demo.config.interceptor;

import com.example.demo.AuditService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.DispatcherType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Component
public class AuditInterceptor extends HandlerInterceptorAdapter {

    private AuditService auditService;

    @Autowired
    public AuditInterceptor(AuditService auditService) {
        this.auditService = auditService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name())) {
            auditRequest(request);
        }
        return super.preHandle(request, response, handler);
    }

    private void auditRequest(HttpServletRequest request) throws JsonProcessingException {
        UUID uuid = UUID.randomUUID();
        request.setAttribute("uuid", uuid);

        String payload = new ObjectMapper().writeValueAsString(request.getParameterMap());
        //auditService.save(uuid, request.getRequestURI(), request.getMethod(), payload, 0);
    }
}
