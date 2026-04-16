package com.edutech.progressive.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.entity.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
    // Derived methods
    List<Appointment> findByClinic_ClinicId(Integer clinicId);
    List<Appointment> findByPatient_PatientId(Integer patientId);
    List<Appointment> findByStatus(String status);

    // JPQL delete methods 
    @Transactional
    @Modifying
    @Query("DELETE FROM Appointment a WHERE a.patient.patientId = :patientId")
    void deleteByPatientId(Integer patientId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Appointment a WHERE a.clinic.clinicId = :clinicId")
    void deleteByClinicId(Integer clinicId);
}
