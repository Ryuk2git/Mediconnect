package com.edutech.progressive.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.repository.PatientRepository;
import com.edutech.progressive.service.PatientService;

@Service
public class PatientServiceImplJpa implements PatientService {

    public PatientServiceImplJpa() {
    }

    private PatientRepository patientRepository;

    public PatientServiceImplJpa(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Patient> getAllPatients() {
        try {
            return patientRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Integer addPatient(Patient patient) {
        try {
            Patient saved = patientRepository.save(patient);
            return saved.getPatientId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Patient> getAllPatientSortedByName() {
        try {
            List<Patient> list = patientRepository.findAll();
            Collections.sort(list);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Patient getPatientById(int patientId) {
        try {
            return patientRepository.findByPatientId(patientId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updatePatient(Patient patient) {
        try {
            Patient existing = patientRepository.findById(patient.getPatientId()).orElse(null);

            if (existing != null) {
                existing.setFullName(patient.getFullName());
                existing.setDateOfBirth(patient.getDateOfBirth());
                existing.setContactNumber(patient.getContactNumber());
                existing.setEmail(patient.getEmail());
                existing.setAddress(patient.getAddress());

                patientRepository.save(existing);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePatient(int patientId) {
        try {
            if (patientRepository.existsById(patientId)) {
                patientRepository.deleteById(patientId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}