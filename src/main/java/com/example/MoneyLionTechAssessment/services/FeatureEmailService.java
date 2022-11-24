package com.example.MoneyLionTechAssessment.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MoneyLionTechAssessment.models.*;
import com.example.MoneyLionTechAssessment.repositories.*;

@Service
public class FeatureEmailService {

    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private FeatureEmailRepository featureEmailRepository;

    public Optional<FeatureEmail> findByFeature_FeatureNameAndEmail_Email(String featureName, String Email){
        return featureEmailRepository.findByFeature_FeatureNameAndEmail_Email(featureName, Email);
    }

    /**
    * This services by default will create feature or email to DB if does not exists.
    * @return   indicate any changes done to DB or not, true means there is changes, false otherwise 
    */
    public Boolean toggleFeaturebyEmail(String queryFeatureName, String queryEmail, Boolean queryToggleEnable){
        FeatureEmail featureEmail;
        var getFeatureEmail = featureEmailRepository.findByFeature_FeatureNameAndEmail_Email(queryFeatureName, queryEmail);
        if(getFeatureEmail.isPresent()){
            featureEmail = getFeatureEmail.get();

            // check if enable status is same
            if(featureEmail.isEnable() == queryToggleEnable){
                // no changes
                return false;
            }
            else{
                featureEmail.setEnable(queryToggleEnable);
                featureEmailRepository.save(featureEmail);
            }
        }
        else if(queryToggleEnable == false){
            // current record does not contain query data, 
            // and toggle is false, 
            // therefore no changes to db needed
            return false;
        }
        else{
            // get feature
            Feature feature;
            var getFeature = featureRepository.findByFeatureName(queryFeatureName);
            if(getFeature.isPresent()){
                feature = getFeature.get();
            }
            else{
                feature = new Feature(queryFeatureName);
                featureRepository.save(feature);
            }

            // get email
            Email email;
            var getEmail = emailRepository.findByEmail(queryEmail);
            if(getEmail.isPresent()){
                email = getEmail.get();
            }
            else{
                email = new Email(queryEmail);
                emailRepository.save(email);
            }

            // create new record
            featureEmailRepository.save(new FeatureEmail(feature, email, queryToggleEnable));
        }
        // successfully change
        return true;
    }

}
