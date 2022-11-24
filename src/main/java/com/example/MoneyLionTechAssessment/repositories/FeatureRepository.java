package com.example.MoneyLionTechAssessment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.MoneyLionTechAssessment.models.Feature;

import java.util.Optional;

@Repository
public interface FeatureRepository extends CrudRepository<Feature, Long> {
    
    Optional<Feature> findById(long id);
    Optional<Feature> findByFeatureName(String featureName);
}