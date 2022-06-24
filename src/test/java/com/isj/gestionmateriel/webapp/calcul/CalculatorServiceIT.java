package com.isj.gestionmateriel.webapp.calcul;

import com.isj.gestionmateriel.webapp.calcul.model.CalculationModel;
import com.isj.gestionmateriel.webapp.calcul.model.CalculationType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CalculatorServiceIT {

	@Autowired
	private  Calculator calculator ;
	@Autowired
	private SolutionFormatter formatter ;

	// Initialiser la classe à tester
	@Autowired
	private  CalculatorService underTest;

	@Test
	public void calculatorService_shouldCalculateASolution_whenGivenACalculationModel() {
		// GIVEN
		final CalculationModel calculation = new CalculationModel(CalculationType.ADDITION,
				100, 101);
		// WHEN
		final CalculationModel result = underTest.calculate(calculation);

		// THEN
		assertThat(result.getSolution()).isEqualTo(201);
	}
}
