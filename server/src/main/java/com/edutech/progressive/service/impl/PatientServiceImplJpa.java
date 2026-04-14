package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.repository.PatientRepository;
import com.edutech.progressive.service.PatientService;
@Service
@Primary
public class PatientServiceImplJpa implements PatientService {

    @Autowired
    private final PatientRepository pr;
    public PatientServiceImplJpa(PatientRepository pr){
        this.pr = pr;
    }
    @Override
    public List<Patient> getAllPatients() throws SQLException {
        return pr.findAll();
    }
    @Override
    public Integer addPatient(Patient patient) throws SQLException {
        Patient p = pr.save(patient);
        return p.getPatientId();
    }
    @Override
    public List<Patient> getAllPatientSortedByName() throws SQLException {
        return pr.findAllPatientSortedByName();
    }

    public void updatePatient(Patient patient) {
        pr.save(patient);
    }

    public void deletePatient(int patientId) {
        if(pr.findById(patientId).isPresent()){
            pr.deleteById(patientId);
        }
    }

    public Patient getPatientById(int patientId) {
        if(pr.findById(patientId).isPresent()){
            return pr.findById(patientId).get();
        }
        return null;
    }
    

}