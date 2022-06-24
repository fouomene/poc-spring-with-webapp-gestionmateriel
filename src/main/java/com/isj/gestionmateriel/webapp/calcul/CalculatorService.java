package com.isj.gestionmateriel.webapp.calcul;


import com.isj.gestionmateriel.webapp.calcul.model.CalculationModel;

public interface CalculatorService {
	/**
	 * Effectuer le calcul demandé par un modèle
	 * 
	 * @param Modele de calcul
	 * @return Modèle de calcul rempli du résultat 
	 */
	public CalculationModel calculate(CalculationModel calculationModel);
}
