/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Deeplight
 */
public class Period {

    int id;
    String periodType;
    float montantBrut, usageTaux, montantImpot;
    int idVersement;

    public Period(int id, String periodType, float montantBrut, float usageTaux, float montantImpot, int idVersement) {
        this.id = id;
        this.periodType = periodType;
        this.montantBrut = montantBrut;
        this.usageTaux = usageTaux;
        this.montantImpot = montantImpot;
        this.idVersement = idVersement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public float getMontantBrut() {
        return montantBrut;
    }

    public void setMontantBrut(float montantBrut) {
        this.montantBrut = montantBrut;
    }

    public float getUsageTaux() {
        return usageTaux;
    }

    public void setUsageTaux(float usageTaux) {
        this.usageTaux = usageTaux;
    }

    public float getMontantImpot() {
        return montantImpot;
    }

    public void setMontantImpot(float montantImpot) {
        this.montantImpot = montantImpot;
    }

    public int getIdVersement() {
        return idVersement;
    }

    public void setIdVersement(int idVersement) {
        this.idVersement = idVersement;
    }

   

    

    
}
