package com.isj.gestionmateriel.webapp.exception;

import org.zalando.problem.Status;

import java.util.Arrays;
import java.util.List;

/**
 * The Class AfrinnovTechnicalException.
 */
public class AfrinnovBusinessException extends Exception {

    private static final long serialVersionUID = 1L;
    private final Status httpStatus;
    private List<String> messages;


    public AfrinnovBusinessException(ErrorInfo errorInfo, String... messages) {
        super(errorInfo.getMessage());
        this.httpStatus = errorInfo.getHttpStatus();
        this.messages = Arrays.asList(messages);
    }

    /**
     * @param msg
     * @param httpStatus
     */
    public AfrinnovBusinessException(String msg, Status httpStatus) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    /**
     * @param cause
     * @param httpStatus
     */
    public AfrinnovBusinessException(Throwable cause, Status httpStatus) {
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
