/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author Deeplight
 */
public class Contract {

    int id, id_proprietor, id_beneficiary, id_habitation;
    String proprietorName, beneficiaryName, contractType;
    Date date, endDate;
    float amount;
    String SteelNumber, periodesImpositiom;

    public Contract(int id, int id_proprietor, int id_beneficiary, int id_habitation, String proprietorName, String beneficiaryName, String contractType, Date date, Date endDate, float amount, String SteelNumber, String periodesImpositiom) {
        this.id = id;
        this.id_proprietor = id_proprietor;
        this.id_beneficiary = id_beneficiary;
        this.id_habitation = id_habitation;
        this.proprietorName = proprietorName;
        this.beneficiaryName = beneficiaryName;
        this.contractType = contractType;
        this.date = date;
        this.endDate = endDate;
        this.amount = amount;
        this.SteelNumber = SteelNumber;
        this.periodesImpositiom = periodesImpositiom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_habitation() {
        return id_habitation;
    }

    public void setId_habitation(int id_habitation) {
        this.id_habitation = id_habitation;
    }

    public int getId_proprietor() {
        return id_proprietor;
    }

    public void setId_proprietor(int id_proprietor) {
        this.id_proprietor = id_proprietor;
    }

    public int getId_beneficiary() {
        return id_beneficiary;
    }

    public void setId_beneficiary(int id_beneficiary) {
        this.id_beneficiary = id_beneficiary;
    }

    public String getProprietorName() {
        return proprietorName;
    }

    public void setProprietorName(String proprietorName) {
        this.proprietorName = proprietorName;
    }

    public String getBeneficiaryName() {
        return beneficiaryName;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getSteelNumber() {
        return SteelNumber;
    }

    public void setSteelNumber(String SteelNumber) {
        this.SteelNumber = SteelNumber;
    }

    public String getPeriodesImpositiom() {
        return periodesImpositiom;
    }

    public void setPeriodesImpositiom(String periodesImpositiom) {
        this.periodesImpositiom = periodesImpositiom;
    }

}
