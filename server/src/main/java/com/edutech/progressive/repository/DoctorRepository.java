package com.edutech.progressive.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
    // List<Doctor> findAllByOrderByExperienceDesc();

     Doctor findByEmail(String email);
    Doctor findByDoctorId(int doctorId);
}
