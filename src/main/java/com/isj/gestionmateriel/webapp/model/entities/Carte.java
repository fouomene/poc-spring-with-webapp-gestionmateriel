package com.isj.gestionmateriel.webapp.model.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Carte {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String lastname;
    private String firstname;
    private String cniNumber;
    private String receiptNumber;
    private String birthDate;
    private String place;
    private String fatherName;
    private String motherName;
    private String profession;
    private String address;
    private String sexe;
    private String height;
    private String deliveryDate;
    private String validityDate;
    private String posteDeCollecte;
    private String givenName;
    private String photo;
    private String signature;
}
