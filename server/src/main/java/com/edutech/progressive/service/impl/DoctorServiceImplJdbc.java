package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.edutech.progressive.dao.DoctorDAO;
import com.edutech.progressive.entity.Doctor;
import com.edutech.progressive.service.DoctorService;

public class DoctorServiceImplJdbc implements DoctorService {

    private DoctorDAO doctorDao;

    public DoctorServiceImplJdbc(DoctorDAO doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        try {
            return doctorDao.getAllDoctors();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Integer addDoctor(Doctor doctor) {
        try {
            return doctorDao.addDoctor(doctor);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public List<Doctor> getDoctorSortedByExperience() {
        try {
            List<Doctor> doctors = doctorDao.getAllDoctors();
            if (doctors != null) {
                // Uses the compareTo method implemented in the Doctor entity 
                // (which compares yearsOfExperience)
                Collections.sort(doctors);
            }
            return doctors;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

}