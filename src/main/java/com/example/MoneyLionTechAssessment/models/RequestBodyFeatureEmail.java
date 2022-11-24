package com.example.MoneyLionTechAssessment.models;

public class RequestBodyFeatureEmail {

    public String featureName;
    public String email;
    public Boolean enable;

    public RequestBodyFeatureEmail(String featureName, String email, Boolean enable){
        this.featureName = featureName;
        this.email = email;
        this.enable = enable;
    }

    @Override
    public String toString() {
      return "Feature{" + "featureName=" + this.featureName + ", email='" + this.email + '\'' + ", enable='" + this.enable + '\'' + '}';
    }

}
