package com.example.MoneyLionTechAssessment.models;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "Feature")
public class Feature {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy="feature")
    Set<FeatureEmail> emails;

    @Column(unique=true)
    private String featureName;

    public Feature(){
    }

    public Feature(String featureName){
        this.featureName = featureName;
    }

    public String getFeatureName(){
        return this.featureName;
    }

    @Override
    public String toString() {
      return "Feature{" + "featureName=" + this.featureName + '\'' + '}';
    }

}