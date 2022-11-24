package com.example.MoneyLionTechAssessment.models;

import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "Email")
public class Email {
    
    @Id
    private String email;

    @OneToMany(mappedBy = "email")
    Set<FeatureEmail> features;

    public Email(){
    }

    public Email(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    @Override
    public String toString() {
      return "Email{" + "email='" + this.email + '\'' + '}';
    }

}
