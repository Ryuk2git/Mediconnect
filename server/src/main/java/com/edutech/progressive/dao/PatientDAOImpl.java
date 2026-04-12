package com.edutech.progressive.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Patient;

public class PatientDAOImpl implements PatientDAO {
    private Connection connection;

    public PatientDAOImpl() {
        
    }

    @Override
    public int addPatient(Patient patient) {
        String query = "INSERT INTO patient (full_name, date_of_birth, contact_number, email, address) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, patient.getFullName());

            if (patient.getDateOfBirth() != null) {
                ps.setDate(2, new java.sql.Date(patient.getDateOfBirth().getTime()));
            } else {
                ps.setNull(2, Types.DATE);
            }

            ps.setString(3, patient.getContactNumber());
            ps.setString(4, patient.getEmail());
            ps.setString(5, patient.getAddress());

            ps.executeUpdate();

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
    public Patient getPatientById(int patientId) {
        String query = "SELECT * FROM patient WHERE patient_id = ?";

        try (Connection connection = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, patientId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("full_name"),
                        rs.getDate("date_of_birth"),
                        rs.getString("contact_number"),
                        rs.getString("email"),
                        rs.getString("address"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void updatePatient(Patient patient) {
        String query = "UPDATE patient SET full_name=?, date_of_birth =?, contact_number=?, email=?, address=? WHERE patient_id=?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, patient.getFullName());
            if (patient.getDateOfBirth() != null) {
                ps.setDate(2, new java.sql.Date(patient.getDateOfBirth().getTime()));
            } else {
                ps.setNull(2, Types.DATE);
            }
            ps.setString(3, patient.getContactNumber());
            ps.setString(4, patient.getEmail());
            ps.setString(5, patient.getAddress());
            ps.setInt(6, patient.getPatientId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePatient(int patientId) {
        String query = "DELETE FROM patient WHERE patient_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, patientId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Patient> getAllPatients() {
        final String sql = "SELECT patient_id, full_name, date_of_birth, contact_number, email, address FROM patient";

        List<Patient> patients = new ArrayList<>();

        try (Connection connection = DatabaseConnectionManager.getConnection();
                PreparedStatement ps = connection.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Date sqlDate = rs.getDate("date_of_birth");
                Date utilDate = (sqlDate == null) ? null : new Date(sqlDate.getTime());

                Patient p = new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("full_name"),
                        utilDate,
                        rs.getString("contact_number"),
                        rs.getString("email"),
                        rs.getString("address"));
                patients.add(p);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return patients;
    }

}
