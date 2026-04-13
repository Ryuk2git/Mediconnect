package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Patient;
import com.edutech.progressive.service.PatientService;

@Service
public class PatientServiceImplArraylist implements PatientService {
    List<Patient> list = new ArrayList<Patient>();

    @Override
    public List<Patient> getAllPatients() {
        return list;
    }

    @Override
    public Integer addPatient(Patient patient) {
        list.add(patient);
        return 1;
    }

    @Override
    public List<Patient> getAllPatientSortedByName() {
        Collections.sort(list);
        return list;
    }

}