package com.isj.gestionmateriel.webapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterielDTO {
    private String reference;
    private String nom;
    private String dateAcquisition;
    private String etat;
    private String description;
}
