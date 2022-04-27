package com.isj.gestionmateriel.webapp.exception;

import org.zalando.problem.Status;

/**
 * The Class AfrinnovTechnicalException.
 */
public class AfrinnovTechnicalException extends RuntimeException{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	private final Status httpStatus;

	/**
	 * Instantiates a new one logic technical exception.
	 */
	public AfrinnovTechnicalException(Status httpStatus) {
		super();
		this.httpStatus=httpStatus;
	}

	/**
	 * @param msg
	 * @param httpStatus
	 */
	public AfrinnovTechnicalException(String msg, Status httpStatus) {
		super(msg);
		this.httpStatus = httpStatus;
	}

	/**
	 * @param cause
	 * @param httpStatus
	 */
	public AfrinnovTechnicalException(Throwable cause, Status httpStatus) {
		super(cause);
		this.httpStatus = httpStatus;
	}

	/**
	 * @return the httpStatus
	 */
	public Status getHttpStatus() {
		return httpStatus;
	}





}
