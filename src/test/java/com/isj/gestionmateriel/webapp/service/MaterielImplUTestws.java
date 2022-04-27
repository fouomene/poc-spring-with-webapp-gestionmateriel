package com.isj.gestionmateriel.webapp.service;

import com.isj.gestionmateriel.webapp.exception.AfrinnovBusinessException;
import com.isj.gestionmateriel.webapp.model.dto.MaterielDTO;
import com.isj.gestionmateriel.webapp.model.entities.Materiel;
import com.isj.gestionmateriel.webapp.repository.MaterielRepository;
import com.isj.gestionmateriel.webapp.service.mapper.MaterielMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MaterielImplUTestws {

    @Autowired
    IMateriel iMateriel;

    @Autowired
    private MaterielMapper materielMapper;

    @Autowired
    private MaterielRepository materielRepository;

    @Test
    public void should_save_materiel() throws AfrinnovBusinessException {
        //Given
        MaterielDTO materielDTO = MaterielDTO.builder()
                .reference("ref123")
                .nom("laptop")
                .dateAcquisition("12/02/2021")
                .etat("Neuf")
                .description("ordinateur de bureau")
                .build();

        //When
        int idMateriel = iMateriel.ajouterMateriel(materielDTO);

        //Then
        assertThat(idMateriel).isNotNull();
        //assertEquals(idMateriel,1);
    }

    @Test
    public void should_failled_when_materiel_not_found() throws AfrinnovBusinessException {
        //Given
        String reference="ref12536";

        //When
        final Throwable result =  catchThrowable(()->iMateriel.detailMateriel(reference));;

        //Then
        assertThat(result).isNotNull();
        assertThat(result.getMessage()).isEqualTo("#MATERIEL_NOT_FOUND");
    }

    @Test
    public void should_found_materiel_by_reference() throws AfrinnovBusinessException {
        //Given
        MaterielDTO materielDTO = MaterielDTO.builder()
                .reference("ref123")
                .nom("laptop")
                .build();
        iMateriel.ajouterMateriel(materielDTO);

        //When
        MaterielDTO materielDTOResult = iMateriel.detailMateriel(materielDTO.getReference());

        //Then
        assertThat(materielDTOResult).isNotNull();
        assertThat(materielDTOResult.getNom()).isEqualTo("laptop");
    }


}
