package com.isj.gestionmateriel.webapp.calcul;


import com.isj.gestionmateriel.webapp.calcul.model.Calculation;
import com.isj.gestionmateriel.webapp.calcul.model.CalculationModel;
import com.isj.gestionmateriel.webapp.calcul.model.CalculationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CalculatorController {

	public static final String CALCULATOR_TEMPLATE = "calculator";

	@Autowired
	CalculatorService calculatorService;

	/*@GetMapping("/")
	public String index(Calculation calculation) {
		return "redirect:/calculator";
	}*/

	@GetMapping("/calculator")
	public String root(Calculation calculation) {
		return CALCULATOR_TEMPLATE; // cf. resources/templates/calculator.html
	}

	@PostMapping("/calculator")
	public String calculate(@Valid Calculation calculation, BindingResult bindingResult, Model model) {

		final CalculationType type = CalculationType.valueOf(calculation.getCalculationType());
		final CalculationModel calculationModel = new CalculationModel(
				type,
				calculation.getLeftArgument(),
				calculation.getRightArgument());

		final CalculationModel response = calculatorService.calculate(calculationModel);

		model.addAttribute("response", response);
		return CALCULATOR_TEMPLATE; // cf. resources/templates/calculator.html
	}
}
