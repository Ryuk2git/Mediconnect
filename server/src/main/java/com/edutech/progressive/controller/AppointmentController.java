package com.edutech.progressive.controller;

import com.edutech.progressive.entity.Appointment;
import com.edutech.progressive.service.AppointmentService;

import org.springframework.http.ResponseEntity;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Get All Appointments
    // Return OK 200 and List<Appointment>
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointment() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    // Create Appointment
    // Return Created 201 and crated appointment
    @PostMapping
    public ResponseEntity<Integer> createAppointment(@RequestBody Appointment appointment) {
        int newId = appointmentService.createAppointment(appointment);
        return ResponseEntity.status(201).body(newId);
    }
    
    // Update Appointment
    // Return OK 200 and Void
    @PutMapping("/{appointmentID}")
    public ResponseEntity<Void> updateAppointment(@PathVariable int appointmentID, @RequestBody Appointment appointment) {
        appointment.setAppointmentId(appointmentID);
        appointmentService.updateAppointment(appointment);
        return ResponseEntity.ok().build();
    }

    // Get Appointment by id
    // Return Ok 200 and Appointment
    @GetMapping("/{appointmentID}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable int appointmentID) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentID);
        return ResponseEntity.ok(appointment);
    }

    // Get Appointment by Clinic
    // Return OK 200 and List of Appointment
    @GetMapping("/clinic/{clinicID}")
    public ResponseEntity<List<Appointment>> getAppointmentByClinic(@PathVariable int clinicID) {
        return ResponseEntity.ok(appointmentService.getAppointmentByClinic(clinicID));
    }

    // Get Appointment by Patient
    // Return Ok 200 and List of Appointment
    @GetMapping("/patient/{patientID}")
    public ResponseEntity<List<Appointment>> getAppointmentByPatient(@PathVariable int patientID) {
        return ResponseEntity.ok(appointmentService.getAppointmentByPatient(patientID));
    }

    // Get Appointment by Status: 
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Appointment>> getAppointmentByStatus(@PathVariable String status) {
        return ResponseEntity.ok(appointmentService.getAppointmentByStatus(status));
    }
}