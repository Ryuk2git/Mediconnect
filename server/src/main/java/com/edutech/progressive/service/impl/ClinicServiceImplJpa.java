package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Clinic;
import com.edutech.progressive.service.ClinicService;


@Service
public class ClinicServiceImplJpa implements ClinicService {

    @Override
    public List<Clinic> getAllClinics() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllClinics'");
    }

    @Override
    public Clinic getClinicById(int clinicId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClinicById'");
    }

    @Override
    public Integer addClinic(Clinic clinic) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addClinic'");
    }

    @Override
    public void updateClinic(Clinic clinic) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateClinic'");
    }

    @Override
    public void deleteClinic(int clinicId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteClinic'");
    }

}