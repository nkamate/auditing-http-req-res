package com.example.demo.repository;

import com.example.demo.entity.Audit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuditRepository extends CrudRepository<Audit, UUID> {
}
