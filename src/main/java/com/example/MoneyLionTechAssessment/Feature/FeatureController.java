package com.example.MoneyLionTechAssessment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeatureController {
    
    @GetMapping("/feature")
    public Boolean feature(
        @RequestParam(value = "email", defaultValue="") String email,
        @RequestParam(value = "featureName", defaultValue="") String featureName
    ){
        return new Feature(email, featureName, false).isEnable();
    }

    @PostMapping("/feature")
    public ResponseEntity<?> createFeature(@RequestBody Feature feature){
        try {
            System.out.println(feature);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

}