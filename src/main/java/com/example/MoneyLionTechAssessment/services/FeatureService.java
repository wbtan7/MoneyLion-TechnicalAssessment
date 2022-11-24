package com.example.MoneyLionTechAssessment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MoneyLionTechAssessment.models.Feature;
import com.example.MoneyLionTechAssessment.repositories.FeatureRepository;

import java.util.Optional;

@Service
public class FeatureService {
    
    @Autowired
    private FeatureRepository featureRepository;

    public Optional<Feature> findByFeatureName(String featureName){
        return featureRepository.findByFeatureName(featureName);
    }

    public void addFeature(Feature feature){
        featureRepository.save(feature);
    }

}
