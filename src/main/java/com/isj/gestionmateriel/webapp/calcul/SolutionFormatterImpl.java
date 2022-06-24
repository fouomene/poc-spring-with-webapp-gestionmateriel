package com.isj.gestionmateriel.webapp.calcul;

import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class SolutionFormatterImpl implements SolutionFormatter {

	@Override
	public String format(int solution) {
		return String.format(Locale.FRENCH, "%,d", solution);
	}
}
