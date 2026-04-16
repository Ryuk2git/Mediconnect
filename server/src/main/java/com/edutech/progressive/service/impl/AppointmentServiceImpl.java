package com.edutech.progressive.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Appointment;
import com.edutech.progressive.repository.AppointmentRepository;
import com.edutech.progressive.service.AppointmentService;

@Service
public class AppointmentServiceImpl  implements AppointmentService{

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public int createAppointment(Appointment appointment) {
        Appointment app = appointmentRepository.save(appointment);
        return app.getAppointmentId();
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        Appointment existing = appointmentRepository.findById(appointment.getAppointmentId()).orElseThrow(() -> new RuntimeException("Appointment not found"));
        
        existing.setAppointmentDate(appointment.getAppointmentDate());
        existing.setStatus(appointment.getStatus());
        existing.setPurpose(appointment.getPurpose());
        existing.setClinic(appointment.getClinic());
        existing.setPatient(appointment.getPatient());

        appointmentRepository.save(existing);
    }

    @Override
    public Appointment getAppointmentById(int appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    @Override
    public List<Appointment> getAppointmentByClinic(int clinicId) {
        return appointmentRepository.findByClinic_ClinicId(clinicId);
    }

    @Override
    public List<Appointment> getAppointmentByPatient(int patientId) {
        return appointmentRepository.findByPatient_PatientId(patientId);
    }

    @Override
    public List<Appointment> getAppointmentByStatus(String status) {
        return appointmentRepository.findByStatus(status);
    }

}