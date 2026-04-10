package com.edutech.progressive.dao;

import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.entity.Clinic;

public class ClinicDAOImpl implements ClinicDAO {

    @Override
    public int addClinic(Clinic clinic) {
        return -1;
    }

    @Override
    public Clinic getClinicById(int clinicId) {
       return null;
    }

    @Override
    public void updateClinic(Clinic clinic) {
        // no imp for now
    }

    @Override
    public void deleteClinic(int clinicId) {
        // no imp for now
    }

    @Override
    public List<Clinic> getAllClinics() {
        return new ArrayList<Clinic>();
    }

}
