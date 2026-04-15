package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Clinic;
import com.edutech.progressive.exception.ClinicAlreadyExistsException;
import com.edutech.progressive.repository.ClinicRepository;
import com.edutech.progressive.service.ClinicService;

@Service
public class ClinicServiceImplJpa implements ClinicService {

    private final ClinicRepository clinicRepository;

    public ClinicServiceImplJpa(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @Override
    public List<Clinic> getAllClinics() throws SQLException {
        return clinicRepository.findAll();

    }

    @Override
    public Clinic getClinicById(int clinicId) throws SQLException {
        return clinicRepository.findByClinicId(clinicId)
                .orElseThrow(() -> new RuntimeException("Clinic not found with id: " + clinicId));
    }

    @Override
    public Integer addClinic(Clinic clinic) throws SQLException {
        if (clinicRepository.findByClinicName(clinic.getClinicName()).isPresent()) {
            throw new ClinicAlreadyExistsException("Clinic already exists with name: " + clinic.getClinicName());
        }
        return clinic.getClinicId();
    }

    public void updateClinic(int clinicId, Clinic clinic) throws SQLException {
        Clinic existing = clinicRepository.findById(clinicId).get();
        if (existing != null) {
            existing.setClinicName(clinic.getClinicName());
            existing.setContactNumber(clinic.getContactNumber());
            existing.setDoctorId(clinic.getDoctorId());
            existing.setEstablishedYear(clinic.getEstablishedYear());
            existing.setLocation(clinic.getLocation());

            clinicRepository.save(existing);
        }
    }

    @Override
    public void deleteClinic(int clinicId) throws SQLException {
        if (clinicRepository.existsById(clinicId)) {
            clinicRepository.deleteById(clinicId);
        }
    }

    @Override
    public void updateClinic(Clinic clinic) throws SQLException {
        if (clinicRepository.existsById(clinic.getClinicId())) {
            clinicRepository.save(clinic);
        }
    }

    @Override
    public List<Clinic> getAllClinicByLocation(String location) {
        return clinicRepository.findAllByLocation(location);
    }

    @Override
    public List<Clinic> getAllClinicByDoctorId(int doctorId) {
        return clinicRepository.findAllByDoctorId(doctorId);
    }
}