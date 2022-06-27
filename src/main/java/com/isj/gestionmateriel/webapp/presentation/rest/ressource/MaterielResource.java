package com.isj.gestionmateriel.webapp.presentation.rest.ressource;

import com.isj.gestionmateriel.webapp.exception.AfrinnovBusinessException;
import com.isj.gestionmateriel.webapp.model.dto.MaterielDTO;
import com.isj.gestionmateriel.webapp.service.IMateriel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiel")
@Slf4j
public class MaterielResource {

	@Autowired
	private IMateriel iMateriel;

	@GetMapping("/nbre")
	public int nbreMateriels() {
		return iMateriel.nbreTotalDeMateriels();
	}

	@PostMapping
	public void enregistrer(@RequestBody MaterielDTO create) throws AfrinnovBusinessException {
		MaterielResource.log.info("enregistrer-materiel");
		iMateriel.ajouterMateriel(create);
	}

	@PatchMapping
	public void miseAjour( @RequestBody MaterielDTO update) throws AfrinnovBusinessException {
		MaterielResource.log.info("mise à jour-materiel");
		iMateriel.modifierMateriel(update);
	}

	@GetMapping("/{reference}/data")
	public ResponseEntity<MaterielDTO> getMaterielByReference(@PathVariable String reference) throws AfrinnovBusinessException{

		return ResponseEntity.ok(iMateriel.detailMateriel(reference));
	}


	@GetMapping("/all")
	public ResponseEntity<List<MaterielDTO>> getAllMateriels() {
		return ResponseEntity.ok(iMateriel.listerMateriels());
	}

	@GetMapping("/{reference}/delete")
	public void deteleMateriel(@PathVariable String reference) throws AfrinnovBusinessException{
		iMateriel.supprimerMateriel(reference);
	}

}