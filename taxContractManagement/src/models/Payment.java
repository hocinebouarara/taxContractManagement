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
public class Payment {

    int id, idContract;
    String lessorName, lessorAdress, nif, nbrAtricle, takerAdress;
    float amount;
    String takerName, occupationTaker;

    public Payment(int id, int idContract, String lessorName, String lessorAdress, String nif, String nbrAtricle, String takerAdress, float amount, String takerName, String occupationTaker) {
        this.id = id;
        this.idContract = idContract;
        this.lessorName = lessorName;
        this.lessorAdress = lessorAdress;
        this.nif = nif;
        this.nbrAtricle = nbrAtricle;
        this.takerAdress = takerAdress;
        this.amount = amount;
        this.takerName = takerName;
        this.occupationTaker = occupationTaker;
    }

    public Payment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdContract() {
        return idContract;
    }

    public void setIdContract(int idContract) {
        this.idContract = idContract;
    }

    public String getLessorName() {
        return lessorName;
    }

    public void setLessorName(String lessorName) {
        this.lessorName = lessorName;
    }

    public String getLessorAdress() {
        return lessorAdress;
    }

    public void setLessorAdress(String lessorAdress) {
        this.lessorAdress = lessorAdress;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNbrAtricle() {
        return nbrAtricle;
    }

    public void setNbrAtricle(String nbrAtricle) {
        this.nbrAtricle = nbrAtricle;
    }

    public String getTakerAdress() {
        return takerAdress;
    }

    public void setTakerAdress(String takerAdress) {
        this.takerAdress = takerAdress;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getTakerName() {
        return takerName;
    }

    public void setTakerName(String takerName) {
        this.takerName = takerName;
    }

    public String getOccupationTaker() {
        return occupationTaker;
    }

    public void setOccupationTaker(String occupationTaker) {
        this.occupationTaker = occupationTaker;
    }

    public void setRecette(String recognition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setInspectoin(String recognition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setWilaya(String recognition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDeriction(String recognition) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
