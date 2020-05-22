package com.example.demo;

import com.example.demo.entity.Audit;
import com.example.demo.repository.AuditRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuditService {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private AuditRepository auditRepository;

    @Autowired
    public AuditService(AuditRepository auditRepository) {
        this.auditRepository = auditRepository;
    }

    public Audit save(UUID uuid, String requestURI, String method, Object payload, int status) {
        Audit audit = new Audit();
        audit.setId(uuid);
        audit.setPayload(payload);
        audit.setMethod(method);
        audit.setStatus(String.valueOf(status));
        audit.setRequestUri(requestURI);
        return save(audit);
    }

    private Audit save(Audit audit) {
        return auditRepository.save(audit);
    }

    private void update(UUID uuid, int status) {
        Optional<Audit> byId = auditRepository.findById(uuid);
        if (byId.isPresent()) {
            Audit audit = byId.get();
            audit.setStatus(String.valueOf(status));
            auditRepository.save(audit);
        }
    }

    public void auditRequest(UUID uuid, HttpServletRequest request) {
        Object payload = getPayload(request);
        save(uuid, request.getRequestURI(), request.getMethod(), payload, 0);
    }

    public void auditResponse(UUID uuid, HttpServletResponse response, Object responseBody) {
        System.out.println(responseBody);
        update(uuid, response.getStatus());
    }

    private Object getPayload(HttpServletRequest request) {
        return request.getParameterMap();
    }
}
