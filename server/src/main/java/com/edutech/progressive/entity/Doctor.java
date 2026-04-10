package com.edutech.progressive.entity;

public class Doctor {
    private int doctorId;
    private String fullName;
    private String speciality;
    private String contactNumber;
    private int yearsOfExperience;

    public Doctor() {
    }

    public Doctor(int doctorId, String fullName, String speciality, String contactNumber, int yearsOfExperience) {
        this.doctorId = doctorId;
        this.fullName = fullName;
        this.speciality = speciality;
        this.contactNumber = contactNumber;
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    

    
}