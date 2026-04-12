package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edutech.progressive.dao.PatientDAO;
import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.service.PatientService;

public class PatientServiceImplJdbc implements PatientService {

   private PatientDAO patientDao;

    public PatientServiceImplJdbc(PatientDAO patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public List<Patient> getAllPatients() {
        try {
            return patientDao.getAllPatients();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Integer addPatient(Patient patient) {
        try {
            return patientDao.addPatient(patient);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Patient> getAllPatientSortedByName() {
        try {
        List<Patient> patients = patientDao.getAllPatients();
        if (patients != null && !patients.isEmpty()) {
            Collections.sort(patients);
            
        }
        return patients;
    } catch (Exception e) {
        e.printStackTrace();
        return new ArrayList<>();
    }
    }

}