package com.example.MoneyLionTechAssessment;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Feature")
public class Feature {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    Set<Email> emails;

    private final String featureName;
    private final String email;
    private final Boolean enable;

    public Feature(String featureName, String email, Boolean enable){
        this.featureName = featureName;
        this.email = email;
        this.enable = enable;
    }

    public String getFeatureName(){
        return this.featureName;
    }

    public String getEmail(){
        return this.email;
    }

    public Boolean isEnable(){
        return this.enable;
    }

    public void setEnable(Boolean enable){
        this.enable = enable;
    }

    @Override
    public String toString() {
      return "Feature{" + "featureName=" + this.featureName + ", email='" + this.email + '\'' + ", enable='" + this.enable + '\'' + '}';
    }

}

@Entity
@Table(name = "Email")
public class Email {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    Set<Feature> features;
}