package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Clinic;
import com.edutech.progressive.service.ClinicService;
import com.edutech.progressive.service.impl.ClinicServiceImplJpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/clinic")
public class ClinicController {

    private final ClinicService clinicService;

    @Autowired
    public ClinicController(ClinicServiceImplJpa clinicServiceImplJpa){
        this.clinicService = clinicServiceImplJpa;
    }

    @GetMapping
    public ResponseEntity<?> getAllClinics() {
        try {
            List<Clinic> clinics = clinicService.getAllClinics();
            return ResponseEntity.ok(clinics);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching clinics");
        }
    }

    @GetMapping("/{clinicId}")
    public ResponseEntity<?> getClinicById(@PathVariable int clinicId) {
        try {
            Clinic clinic = clinicService.getClinicById(clinicId);
            return ResponseEntity.ok(clinic);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching clinic");
        }
    }

    @PostMapping
    public ResponseEntity<?> addClinic(@RequestBody Clinic clinic) {
        try {
            Integer id = clinicService.addClinic(clinic);
            return ResponseEntity.status(HttpStatus.CREATED).body(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding clinic");
        }
    }

    @PutMapping("/{clinicId}")
    public ResponseEntity<?> updateClinic(@PathVariable int clinicId,
            @RequestBody Clinic clinic) {
        try {
            clinic.setClinicId(clinicId); 
            clinicService.updateClinic(clinic);
            return ResponseEntity.ok("Clinic updated");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating clinic");
        }
    }

    @DeleteMapping("/{clinicId}")
    public ResponseEntity<?> deleteClinic(@PathVariable int clinicId) {
        try {
            clinicService.deleteClinic(clinicId);
            return ResponseEntity.ok("Clinic deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting clinic");
        }
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<?> getAllClinicByLocation(@PathVariable String location) {
        try {
            List<Clinic> clinics = clinicService.getAllClinicByLocation(location);
            return ResponseEntity.ok(clinics);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching clinics by location");
        }
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<?> getAllClinicByDoctorId(@PathVariable int doctorId) {
        try {
            List<Clinic> clinics = clinicService.getAllClinicByDoctorId(doctorId);
            return ResponseEntity.ok(clinics);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error fetching clinics by doctor");
        }
    }
}