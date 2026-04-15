package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.exception.PatientAlreadyExistsException;
import com.edutech.progressive.exception.PatientNotFoundException;
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
        if(pr.findByEmail(patient.getEmail()).isPresent()){
            throw new PatientAlreadyExistsException("Patient already exists with email id: " + patient.getEmail());
        }
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
         return pr.findById(patientId).orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + patientId));
    }
    
    @Override
    public Patient getPatientByEmail(String email) {
        Optional<Patient> patientOpt = pr.findByEmail(email);
        if (patientOpt.isPresent()) {
            return patientOpt.get();
        } else {
            throw new PatientNotFoundException("Patient not found with email: " + email);
        }
    }

}