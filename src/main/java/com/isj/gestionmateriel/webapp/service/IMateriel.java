package com.isj.gestionmateriel.webapp.service;

import com.isj.gestionmateriel.webapp.exception.AfrinnovBusinessException;
import com.isj.gestionmateriel.webapp.model.dto.MaterielDTO;
import com.isj.gestionmateriel.webapp.model.dto.TableauBordDto;

import java.util.List;

public interface IMateriel {

    int ajouterMateriel(MaterielDTO materielDTO) throws AfrinnovBusinessException;
    int supprimerMateriel(String reference) throws AfrinnovBusinessException;
    int modifierMateriel(MaterielDTO materielDTO) throws AfrinnovBusinessException;
    List<MaterielDTO> listerMateriels();
    MaterielDTO detailMateriel(String reference) throws AfrinnovBusinessException;
    List<MaterielDTO> rechercherMateriels(String motcle) throws AfrinnovBusinessException;
    int nbreTotalDeMateriels();
    TableauBordDto statistiquePourLeTableauBord();

}
