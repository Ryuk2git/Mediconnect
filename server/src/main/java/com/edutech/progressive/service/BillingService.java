package com.edutech.progressive.service;

import com.edutech.progressive.entity.Billing;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface BillingService {

    List<Billing> getAllBills();

    Billing getBillById(Integer billingId);

    Integer createBill(Billing billing);

    void deleteBill(Integer billingId);

    List<Billing> getBillsByPatientId(Integer patientId);
}
