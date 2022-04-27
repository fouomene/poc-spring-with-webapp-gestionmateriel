package com.isj.gestionmateriel.webapp.presentation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmpruntController {

	@GetMapping("/listeemprunts")
	public String listerEmprunts(Model model) {

		// appel de la methode de la couche service
		// qui retourne la liste des emprunts

		return "listeemprunt";
	}


	@GetMapping("/detailemprunt")
	public String detailEmprunt(@RequestParam(name="idemprunt") String idEmprunt, Model model) {


		// appel de la méthode de la couche service qui retourne
		// un objet Emprunt en fonction de son id

		return "detailemprunt";
	}




}