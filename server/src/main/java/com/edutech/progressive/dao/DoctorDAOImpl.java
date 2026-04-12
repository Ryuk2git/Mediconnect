package com.edutech.progressive.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Doctor;

public class DoctorDAOImpl implements DoctorDAO {

    @Override
    public int addDoctor(Doctor doctor) {
        String query = "INSERT INTO doctor (full_name, specialty, contact_number, email, years_of_experience) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, doctor.getFullName());
            ps.setString(2, doctor.getSpecialty());
            ps.setString(3, doctor.getContactNumber());
            ps.setString(4, doctor.getEmail());
            ps.setInt(5, doctor.getYearsOfExperience());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) return -1;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1); 
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Doctor getDoctorById(int doctorId) {
        String query = "SELECT * FROM doctor WHERE doctor_id = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, doctorId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("full_name"),
                        rs.getString("specialty"),
                        rs.getString("contact_number"),
                        rs.getString("email"),
                        rs.getInt("years_of_experience")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateDoctor(Doctor doctor) {
        String query = "UPDATE doctor SET full_name = ?, specialty = ?, contact_number = ?, email = ?, years_of_experience = ? WHERE doctor_id = ?";
        try (Connection connection = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, doctor.getFullName());
            ps.setString(2, doctor.getSpecialty());
            ps.setString(3, doctor.getContactNumber());
            ps.setString(4, doctor.getEmail());
            ps.setInt(5, doctor.getYearsOfExperience());
            ps.setInt(6, doctor.getDoctorId());
            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("No doctor found to update.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDoctor(int doctorId) {
        String query = "DELETE FROM doctor WHERE doctor_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, doctorId);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("No doctor found to delete.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM doctor";
        try (Connection connection = DatabaseConnectionManager.getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                doctors.add(new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("full_name"),
                        rs.getString("specialty"),
                        rs.getString("contact_number"),
                        rs.getString("email"),
                        rs.getInt("years_of_experience")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctors;
    }
}