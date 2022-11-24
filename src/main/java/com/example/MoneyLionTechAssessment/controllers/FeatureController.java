package com.example.MoneyLionTechAssessment.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MoneyLionTechAssessment.models.*;
import com.example.MoneyLionTechAssessment.services.*;
import com.example.MoneyLionTechAssessment.repositories.*;

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class FeatureController {
    
    @Autowired
    private FeatureService featureService;
    @Autowired
    private EmailRepository emailRepository;
    @Autowired
    private FeatureEmailRepository featureEmailRepository;

    @GetMapping("/feature")
    public Map<String, Boolean> feature(
        @RequestParam(value = "email", defaultValue="") String email,
        @RequestParam(value = "featureName", defaultValue="") String featureName
    ){
        // default canAccess to false
        Map<String, Boolean> map = new HashMap<>();
        map.put("canAccess", false);

        // if record exist in db, use it instead
        featureEmailRepository.findByFeature_FeatureNameAndEmail_Email(featureName, email).ifPresent(user -> map.put("canAccess", user.isEnable()));
        return map;
    }

    @PostMapping("/feature")
    public ResponseEntity<?> createFeature(@RequestBody RequestBodyFeatureEmail requestFeatureEmail){
        try {
            
            FeatureEmail featureEmail;
            var getFeatureEmail = featureEmailRepository.findByFeature_FeatureNameAndEmail_Email(requestFeatureEmail.featureName, requestFeatureEmail.email);
            if(getFeatureEmail.isPresent()){
                featureEmail = getFeatureEmail.get();

                // enable status is same
                if(featureEmail.isEnable() == requestFeatureEmail.enable){
                    return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
                }
                else{
                    featureEmail.setEnable(requestFeatureEmail.enable);
                    featureEmailRepository.save(featureEmail);
                }
            }
            else{
                Feature feature;
                var getFeature = featureService.findByFeatureName(requestFeatureEmail.featureName);
                if(getFeature.isPresent()){
                    feature = getFeature.get();
                }
                else{
                    feature = new Feature(requestFeatureEmail.featureName);
                    featureService.addFeature(feature);
                }
    
                Email email;
                var getEmail = emailRepository.findByEmail(requestFeatureEmail.email);
                if(getEmail.isPresent()){
                    email = getEmail.get();
                }
                else{
                    email = new Email(requestFeatureEmail.email);
                    emailRepository.save(email);
                }

                // create new record
                featureEmailRepository.save(new FeatureEmail(feature, email, requestFeatureEmail.enable));
            }

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

}