package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.dao.ClinicDAO;
import com.edutech.progressive.entity.Clinic;
import com.edutech.progressive.service.ClinicService;

public class ClinicServiceImplJdbc implements ClinicService {

   private ClinicDAO clinicDao;

    public ClinicServiceImplJdbc(ClinicDAO clinicDao) {
        this.clinicDao = clinicDao;
    }

    @Override
    public List<Clinic> getAllClinics() {
        try {
            return clinicDao.getAllClinics();
        } catch (Exception e) {
            System.err.println("Error while fetching all clinics: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Clinic getClinicById(int clinicId) {
        try {
            return clinicDao.getClinicById(clinicId);
        } catch (Exception e) {
            System.err.println("Error while fetching clinic with ID " + clinicId + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public Integer addClinic(Clinic clinic) {
        try {
            return clinicDao.addClinic(clinic);
        } catch (Exception e) {
            System.err.println("Error while adding clinic: " + e.getMessage());
            return -1;
        }
    }

    @Override
    public void updateClinic(Clinic clinic) {
        try {
            clinicDao.updateClinic(clinic);
        } catch (Exception e) {
            System.err.println("Error while updating clinic: " + e.getMessage());
        }
    }

    @Override
    public void deleteClinic(int clinicId) {
        try {
            clinicDao.deleteClinic(clinicId);
        } catch (Exception e) {
            System.err.println("Error while deleting clinic: " + e.getMessage());
        }
    }

}