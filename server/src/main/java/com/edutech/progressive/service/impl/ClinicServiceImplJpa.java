package com.edutech.progressive.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Clinic;
import com.edutech.progressive.repository.ClinicRepository;
import com.edutech.progressive.service.ClinicService;

@Service
public class ClinicServiceImplJpa implements ClinicService {

    

    public ClinicServiceImplJpa() {
    }

    @Autowired
    private ClinicRepository clinicRepository;

    @Override
    public List<Clinic> getAllClinics() {
        try {
            return clinicRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Integer addClinic(Clinic clinic) {
        try {
            Clinic saved = clinicRepository.save(clinic);
            return saved.getClinicId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Clinic getClinicById(int clinicId) {
        try {
            return clinicRepository.findById(clinicId).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateClinic(Clinic clinic) {
        try {
            clinicRepository.save(clinic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClinic(int clinicId) {
        try {
            clinicRepository.deleteById(clinicId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}