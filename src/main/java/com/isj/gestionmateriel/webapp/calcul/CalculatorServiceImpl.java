package com.isj.gestionmateriel.webapp.calcul;

import com.isj.gestionmateriel.webapp.calcul.model.CalculationModel;
import com.isj.gestionmateriel.webapp.calcul.model.CalculationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {

	@Autowired
	private Calculator calculator;

	@Autowired
	private  SolutionFormatter solutionFormatter;

	@Override
	public CalculationModel calculate(CalculationModel calculationModel) {
		final CalculationType type = calculationModel.getType();

		Integer response = null;
		switch (type) {
		case ADDITION:
			response = calculator.add(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		case SUBTRACTION:
			response = calculator.sub(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		case MULTIPLICATION:
			response = calculator.multiply(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			break;
		case DIVISION:
			try {
				response = calculator.divide(calculationModel.getLeftArgument(), calculationModel.getRightArgument());
			} catch (final ArithmeticException e) {
				throw new IllegalArgumentException(e);
			}
			break;
		default:
			throw new UnsupportedOperationException("Unsupported calculations");
		}

		calculationModel.setSolution(response);
		calculationModel.setFormattedSolution(solutionFormatter.format(response));
		return calculationModel;
	}

}
