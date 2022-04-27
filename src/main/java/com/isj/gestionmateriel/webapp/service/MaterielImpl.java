package com.isj.gestionmateriel.webapp.service;

import com.isj.gestionmateriel.webapp.exception.AfrinnovBusinessException;
import com.isj.gestionmateriel.webapp.exception.ErrorInfo;
import com.isj.gestionmateriel.webapp.model.dto.MaterielDTO;
import com.isj.gestionmateriel.webapp.model.dto.TableauBordDto;
import com.isj.gestionmateriel.webapp.model.entities.Materiel;
import com.isj.gestionmateriel.webapp.repository.MaterielRepository;
import com.isj.gestionmateriel.webapp.service.mapper.MaterielMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.isj.gestionmateriel.webapp.builder.TableauBordDtoBuilder.anTableauBordDto;

@Service

public class MaterielImpl implements IMateriel {

    @Autowired
    private MaterielMapper materielMapper;

    @Autowired
    private MaterielRepository materielRepository;

    @Override
    public int ajouterMateriel(MaterielDTO materielDTO) throws AfrinnovBusinessException {

        checkReference(materielDTO.getReference());
        checkReferenceAlreadyUsed(materielDTO.getReference());

        return materielRepository.save(materielMapper.toEntity(materielDTO)).getId().intValue();
    }

    @Override
    public int supprimerMateriel(String reference) throws AfrinnovBusinessException {

        checkReference(reference);
        //Materiel materiel = materielRepository.findMaterielByReference(reference).get();

        Materiel materiel = materielRepository.findMaterielByReference(reference)
                .orElseThrow(() -> new AfrinnovBusinessException(ErrorInfo.MATERIEL_NOT_FOUND));

        materielRepository.deleteById(materiel.getId());

       // materielRepository.deleteMateriel(reference);
        return 1;
    }

    @Override
    public int modifierMateriel(MaterielDTO materielDTO) throws AfrinnovBusinessException {

        //rechercher l'endite materiel

        Materiel materiel = materielRepository.findMaterielByReference(materielDTO.getReference())
                .orElseThrow(() -> new AfrinnovBusinessException(ErrorInfo.MATERIEL_NOT_FOUND));


     /*   materiel.setNom(materielDTO.getNom());
        materiel.setEtat(materielDTO.getEtat());
        materiel.setDescription(materielDTO.getDescription());
        materiel.setDateAcquisition(materielDTO.getDateAcquisition());*/

        materielMapper.copy(materielDTO,materiel);

        return materielRepository.save(materiel).getId().intValue();
    }

    @Override
    public List<MaterielDTO> listerMateriels() {

       // List<Materiel> materiels = materielRepository.findAll();
        return getMaterielDTOS(materielRepository.findAll());
    }

    @Override
    public MaterielDTO detailMateriel(String reference) throws AfrinnovBusinessException {


        return materielMapper.toDto(materielRepository.findMaterielByReference(reference)
                .orElseThrow(() -> new AfrinnovBusinessException(ErrorInfo.MATERIEL_NOT_FOUND)));
    }

    @Override
    //@Cacheable(cacheNames = "MATERIELS")
    public List<MaterielDTO> rechercherMateriels(String motcle) throws AfrinnovBusinessException {

        //List<Materiel> materiels = materielRepository.findMaterielByReferenceOrNomOrDescription(motcle,motcle,motcle);


        return getMaterielDTOS(materielRepository.findMaterielByReferenceOrNomOrDescription(motcle,motcle,motcle)
                .orElseThrow(() -> new AfrinnovBusinessException(ErrorInfo.MATERIEL_NOT_FOUND)));

    }

    @Override
    public int nbreTotalDeMateriels() {
        return materielRepository.findAll().size();
    }

    @Override
    public TableauBordDto statistiquePourLeTableauBord() {

        return anTableauBordDto()
                .withMaterielDTOS(listerMateriels())
                .withNbreTotalMateriel(listerMateriels().size())
                .build();
    }


    private List<MaterielDTO> getMaterielDTOS(List<Materiel> materiels) {

       // return materiels.stream().map(materiel -> materielMapper.toDto(materiel)).collect(Collectors.toList());
        return materiels.stream().map(materielMapper::toDto).collect(Collectors.toList());

    }

    private void checkReferenceAlreadyUsed(String reference) throws AfrinnovBusinessException {
        if (materielRepository.existsByReferenceIgnoreCase(reference)) {
            throw new AfrinnovBusinessException(ErrorInfo.REFERENCE_MATERIEL_ALREADY_USED);
        }
    }

    private void checkReference(String reference) throws AfrinnovBusinessException {
        if (Objects.isNull(reference)) {
            throw new AfrinnovBusinessException(ErrorInfo.REFERENCE_MATERIEL_REQUIRED);
        }
    }
}
