package com.isj.gestionmateriel.webapp.presentation.rest;

import com.isj.gestionmateriel.webapp.exception.AfrinnovBusinessException;
import com.isj.gestionmateriel.webapp.model.dto.MaterielDTO;
import com.isj.gestionmateriel.webapp.service.IMateriel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiel")
@Slf4j
public class MaterielRestController {

	@Autowired
	private IMateriel iMateriel;

	@GetMapping("/nbre")
	public int nbreMateriels() {
		return iMateriel.nbreTotalDeMateriels();
	}

	@PostMapping
	public void enregistrer(@RequestBody MaterielDTO create) throws AfrinnovBusinessException {
		MaterielRestController.log.info("enregistrer-materiel");
		iMateriel.ajouterMateriel(create);
	}

	@PatchMapping
	public void miseAjour( @RequestBody MaterielDTO update) throws AfrinnovBusinessException {
		MaterielRestController.log.info("mise à jour-materiel");
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