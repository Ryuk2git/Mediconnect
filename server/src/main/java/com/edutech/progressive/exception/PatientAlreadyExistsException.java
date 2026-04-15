package com.edutech.progressive.exception;

public class PatientAlreadyExistsException extends RuntimeException {
    public PatientAlreadyExistsException(String m){
        super(m);
    }
}