package com.edutech.progressive.exception;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(String m){
        super(m);
    }
}