package com.example.MoneyLionTechAssessment.models;

import java.io.Serializable;
import javax.persistence.*;

@Embeddable
class FeatureEmailKey implements Serializable {

    @Column(name = "feature_id")
    Long featureId;

    @Column(name = "email_id")
    String emailId;
}

@Entity
@Table(name="FeatureEmail")
public class FeatureEmail {
    
    @EmbeddedId
    private FeatureEmailKey id = new FeatureEmailKey();

    @ManyToOne
    @MapsId("featureId")
    @JoinColumn(name="feature_id")
    private Feature feature;

    @ManyToOne
    @MapsId("emailId")
    @JoinColumn(name="email_id")
    private Email email;

    Boolean enable;

    public FeatureEmail(){
    }

    public FeatureEmail(Feature feature, Email email ,Boolean enable){
        this.feature = feature;
        this.email = email;
        this.enable = enable;
    }

    public Boolean isEnable(){
        return this.enable;
    }

    public void setEnable(Boolean enable){
        this.enable = enable;
    }

    @Override
    public String toString() {
      return "Feature{" + "featureName=" + this.feature.getFeatureName() + ", email='" + this.email.getEmail() + '\'' + ", enable='" + this.enable + '\'' + '}';
    }
}