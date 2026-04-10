package com.edutech.progressive.dao;

import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.entity.Patient;

public class PatientDAOImpl implements PatientDAO{

    @Override
    public int addPatient(Patient patient) {
        return -1;
    }

    @Override
    public Patient getPatientById(int patientId) {
        return null;
    }

    @Override
    public void updatePatient(Patient patient) {
        // no implementation
    }

    @Override
    public void deletePatient(int patientId) {
        // no implementation
    }

    @Override
    public List<Patient> getAllPatients() {
        return new ArrayList<Patient>();
    }

}
