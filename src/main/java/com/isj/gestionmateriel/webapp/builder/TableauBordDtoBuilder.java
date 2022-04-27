package com.isj.gestionmateriel.webapp.builder;

import com.isj.gestionmateriel.webapp.model.dto.MaterielDTO;
import com.isj.gestionmateriel.webapp.model.dto.TableauBordDto;

import java.util.List;

public class TableauBordDtoBuilder {

    private List<MaterielDTO> materielDTOS;
    private int nbreTotalMateriel;

    private TableauBordDtoBuilder() {
    }

    public static TableauBordDtoBuilder anTableauBordDto() {
        return new TableauBordDtoBuilder();
    }


    public TableauBordDtoBuilder withMaterielDTOS(List<MaterielDTO> materielDTOS) {
        this.materielDTOS = materielDTOS;
        return this;
    }

    public TableauBordDtoBuilder withNbreTotalMateriel(int  nbreTotalMateriel) {
        this.nbreTotalMateriel = nbreTotalMateriel;
        return this;
    }


    public TableauBordDto build() {
        TableauBordDto tableauBordDto = new TableauBordDto();
        tableauBordDto.setMaterielDTOS(materielDTOS);
        tableauBordDto.setNbreTotalMateriel(nbreTotalMateriel);
        return tableauBordDto;
    }
}