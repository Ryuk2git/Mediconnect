package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Clinic;

public class ClinicDAOImpl implements ClinicDAO {

private Connection connection;

    public ClinicDAOImpl() {
        try {
            this.connection = DatabaseConnectionManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int addClinic(Clinic clinic) {
        String query = "INSERT INTO clinic (clinic_name, location, doctor_id, contact_number, established_year) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, clinic.getClinicName());
            ps.setString(2, clinic.getLocation());
            ps.setInt(3, clinic.getDoctorId());
            ps.setString(4, clinic.getContactNumber());
            ps.setInt(5, clinic.getEstablishedYear());
            
            int affectedRows = ps.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1); 
                    }
                }
            }
            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Clinic getClinicById(int clinicId) {
        String query = "SELECT * FROM clinic WHERE clinic_id = ?";

        try(PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, clinicId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Clinic(
                    rs.getInt("clinic_id"),
                    rs.getString("clinic_name"),
                    rs.getString("location"),
                    rs.getInt("doctor_id"),
                    rs.getString("contact_number"),
                    rs.getInt("established_year")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateClinic(Clinic clinic) {
        String query = "UPDATE clinic SET clinic_name = ?, location = ?, doctor_id = ?, contact_number = ?, established_year = ? WHERE clinic_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, clinic.getClinicName());
            ps.setString(2, clinic.getLocation());
            ps.setInt(3, clinic.getDoctorId());
            ps.setString(4, clinic.getContactNumber());
            ps.setInt(5, clinic.getEstablishedYear());
            ps.setInt(6, clinic.getClinicId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClinic(int clinicId) {
        String query = "DELETE FROM clinic WHERE clinic_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)){
            ps.setInt(1, clinicId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Clinic> getAllClinics() {
        List<Clinic> clinics = new ArrayList<>();
        String query = "SELECT * FROM clinic";
        try (Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                clinics.add(new Clinic(
                    rs.getInt("clinic_id"),
                    rs.getString("clinic_name"),
                    rs.getString("location"),
                    rs.getInt("doctor_id"),
                    rs.getString("contact_number"),
                    rs.getInt("established_year")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clinics;
    }

}
