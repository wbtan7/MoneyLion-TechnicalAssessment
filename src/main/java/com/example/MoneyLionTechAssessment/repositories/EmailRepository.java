package com.example.MoneyLionTechAssessment.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.MoneyLionTechAssessment.models.Email;

@Repository
public interface EmailRepository extends CrudRepository<Email, String> {
    
    Optional<Email> findByEmail(String email);
}