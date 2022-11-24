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

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
public class FeatureController {
    
    @Autowired FeatureEmailService featureEmailService;

    @GetMapping("/feature")
    public Map<String, Boolean> feature(
        @RequestParam(value = "email", defaultValue="") String email,
        @RequestParam(value = "featureName", defaultValue="") String featureName
    ){
        // default canAccess to false
        Map<String, Boolean> map = new HashMap<>();
        map.put("canAccess", false);

        // if record exist in db, use it instead
        featureEmailService.findByFeature_FeatureNameAndEmail_Email(featureName, email).ifPresent(user -> map.put("canAccess", user.isEnable()));
        return map;
    }

    @PostMapping("/feature")
    public ResponseEntity<?> createFeature(@RequestBody RequestBodyFeatureEmail requestFeatureEmail){
        try {
            Boolean success_db_update = featureEmailService.toggleFeaturebyEmail(requestFeatureEmail.featureName, requestFeatureEmail.email, requestFeatureEmail.enable);
            if(success_db_update){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

}