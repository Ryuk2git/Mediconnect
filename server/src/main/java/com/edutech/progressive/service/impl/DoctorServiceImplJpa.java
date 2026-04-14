package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.repository.DoctorRepository;
import com.edutech.progressive.service.DoctorService;


@Service
public class DoctorServiceImplJpa implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImplJpa(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> getAllDoctors() throws SQLException {
        return doctorRepository.findAll();
    }

    @Override
    public Integer addDoctor(Doctor doctor) throws SQLException {
        Doctor saved = doctorRepository.save(doctor);
        return saved.getDoctorId();
    }

    @Override
    public List<Doctor> getDoctorSortedByExperience() throws SQLException {
        return doctorRepository.findAll(Sort.by(Sort.Direction.ASC, "yearsOfExperience"));
    }

    @Override
    public Doctor getDoctorById(int doctorId) throws SQLException {
        return doctorRepository.findById(doctorId).orElse(null);
    }

    public void updateDoctor(int doctorId, Doctor doctor) {
        doctor.setDoctorId(doctorId);
        doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(int doctorId) {
        doctorRepository.deleteById(doctorId);
    }
}
