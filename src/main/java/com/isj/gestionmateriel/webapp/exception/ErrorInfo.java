package com.isj.gestionmateriel.webapp.exception;

import lombok.Getter;
import org.zalando.problem.Status;

@Getter
public enum ErrorInfo {

    REFERENCE_MATERIEL_REQUIRED("#REFERENCE_MATERIEL_REQUIRED", Status.BAD_REQUEST),
    MATERIEL_NOT_FOUND("#MATERIEL_NOT_FOUND", Status.NOT_FOUND),
    REFERENCE_MATERIEL_ALREADY_USED("#REFERENCE_MATERIEL_ALREADY_USED", Status.BAD_REQUEST);

    private final String message;
    private final Status httpStatus;

    ErrorInfo(String message, Status httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
