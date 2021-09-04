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
public class Controle {

    int id, contractId, ficheHabId, proprId;
    String proprName;
    int benefID;
    String benefName, inspection,
            recette, annee, designation;
    int nis, nif;
    String wilaya, activite;
    int codeActivite;
    String formJuridique, Adress;
    int articleImpots;

    public Controle(int id, int contractId, int ficheHabId, int proprId, String proprName, int benefID, String benefName, String inspection, String recette, String annee, String designation, int nis, int nif, String wilaya, String activite, int codeActivite, String formJuridique, String Adress, int articleImpots) {
        this.id = id;
        this.contractId = contractId;
        this.ficheHabId = ficheHabId;
        this.proprId = proprId;
        this.proprName = proprName;
        this.benefID = benefID;
        this.benefName = benefName;
        this.inspection = inspection;
        this.recette = recette;
        this.annee = annee;
        this.designation = designation;
        this.nis = nis;
        this.nif = nif;
        this.wilaya = wilaya;
        this.activite = activite;
        this.codeActivite = codeActivite;
        this.formJuridique = formJuridique;
        this.Adress = Adress;
        this.articleImpots = articleImpots;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getFicheHabId() {
        return ficheHabId;
    }

    public void setFicheHabId(int ficheHabId) {
        this.ficheHabId = ficheHabId;
    }

    public int getProprId() {
        return proprId;
    }

    public void setProprId(int proprId) {
        this.proprId = proprId;
    }

    public String getProprName() {
        return proprName;
    }

    public void setProprName(String proprName) {
        this.proprName = proprName;
    }

    public int getBenefID() {
        return benefID;
    }

    public void setBenefID(int benefID) {
        this.benefID = benefID;
    }

    public String getBenefName() {
        return benefName;
    }

    public void setBenefName(String benefName) {
        this.benefName = benefName;
    }

    public String getInspection() {
        return inspection;
    }

    public void setInspection(String inspection) {
        this.inspection = inspection;
    }

    public String getRecette() {
        return recette;
    }

    public void setRecette(String recette) {
        this.recette = recette;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getNis() {
        return nis;
    }

    public void setNis(int nis) {
        this.nis = nis;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public String getWilaya() {
        return wilaya;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public int getCodeActivite() {
        return codeActivite;
    }

    public void setCodeActivite(int codeActivite) {
        this.codeActivite = codeActivite;
    }

    public String getFormJuridique() {
        return formJuridique;
    }

    public void setFormJuridique(String formJuridique) {
        this.formJuridique = formJuridique;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String Adress) {
        this.Adress = Adress;
    }

    public int getArticleImpots() {
        return articleImpots;
    }

    public void setArticleImpots(int articleImpots) {
        this.articleImpots = articleImpots;
    }

}
