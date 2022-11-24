package com.example.MoneyLionTechAssessment.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.MoneyLionTechAssessment.models.FeatureEmail;

@Repository
public interface FeatureEmailRepository extends CrudRepository<FeatureEmail, Long> {
    
    Optional<FeatureEmail> findByFeature_FeatureNameAndEmail_Email(String featureName, String Email);
}