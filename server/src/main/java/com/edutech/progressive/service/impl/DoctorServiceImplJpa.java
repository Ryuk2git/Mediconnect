package com.edutech.progressive.service.impl;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.repository.DoctorRepository;
import com.edutech.progressive.service.DoctorService;

public class DoctorServiceImplJpa implements DoctorService {
    
    public DoctorServiceImplJpa() {
    }

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public Integer addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor).getDoctorId();
    }

    @Override
    public List<Doctor> getDoctorSortedByExperience() {
        List<Doctor> doctorList = doctorRepository.findAll();
        doctorList.sort(Comparator.comparing(Doctor::getYearsOfExperience));
        return doctorList;
    }

}