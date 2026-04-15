package com.edutech.progressive.exception;

public class DoctorAlreadyExistsException extends RuntimeException {
    public DoctorAlreadyExistsException(String m){
        super(m);
    }
}