package com.isj.gestionmateriel.webapp.presentation.controller;

import com.isj.gestionmateriel.webapp.exception.AfrinnovBusinessException;
import com.isj.gestionmateriel.webapp.model.dto.MaterielDTO;
import com.isj.gestionmateriel.webapp.service.IMateriel;
import com.isj.gestionmateriel.webapp.service.MaterielImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
public class MaterielController {

	@Autowired
	IMateriel iMateriel;

	@GetMapping("/listemateriels")
	public String listerMateriels(Model model) {

		// appel de la couche service pour avoir la liste des matériels
		List<MaterielDTO> materielDTOS = iMateriel.listerMateriels();

		model.addAttribute("materielDTOS", materielDTOS);

		return "listemateriels";
	}

	//affichage du formulaire
	@GetMapping("/ajoutematerielform")
	public String ajourterMaterielForm(Model model) {

		MaterielDTO materielDTO = new MaterielDTO();
        materielDTO.setReference("ref");
		model.addAttribute("materielDTO",materielDTO);

		return "ajoutermateriel";
	}


	// Traitemement des valeurs saisies dans le formulaire
	@PostMapping("/enregistrermateriel")
	public String enregistrerMateriel(@ModelAttribute MaterielDTO materielDTO,
									  Model model) throws AfrinnovBusinessException {

		MaterielController.log.info("enregistrer-materiel");
		// appel de la couche service ou metier injectée pour enregistrer un materiel
		iMateriel.ajouterMateriel(materielDTO);

		return "redirect:listemateriels";


	}



	@GetMapping("/recherchematerielform")
	public String rechercherMaterielForm( @RequestParam(value = "motcles", defaultValue = "") String motcles,
										  Model model) throws AfrinnovBusinessException {

		List<MaterielDTO> materielDTOS = iMateriel.rechercherMateriels(motcles);


		model.addAttribute("materielDTOS",materielDTOS);

		return "recherchermateriel";
	}


	@GetMapping("/detailmateriel")
	public String detailMateriel(@RequestParam(name="refmateriel") String refmateriel, Model model) throws AfrinnovBusinessException {


		// appel de la méthode de la couche service qui retourne
		// un objet matériel en fonction de la reference

		MaterielDTO materielDTO = iMateriel.detailMateriel(refmateriel);

		model.addAttribute("materielDTO",materielDTO);

		return "detailmateriel";
	}


	@GetMapping("/supprimermateriel")
	public String suppimerMateriel(@RequestParam(name="refmateriel") String refmateriel, Model model) throws AfrinnovBusinessException {

		// appel de la méthode de la couche service qui supprime
		// un objet matériel en fonction de la ref

		iMateriel.supprimerMateriel(refmateriel);

		return "redirect:/listemateriels";
	}




}