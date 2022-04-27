package com.isj.gestionmateriel.webapp.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
public class TableauBordDto {
    private List<MaterielDTO> materielDTOS;
    private int nbreTotalMateriel;
}
