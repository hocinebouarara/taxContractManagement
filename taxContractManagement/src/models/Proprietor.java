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
public class Proprietor {

    int id;
    String name;
    Date birthDate;
    String commune, wilaya, prenom_pere, nom_mere, nationnalite, adresse_domicile, phone;

    boolean update = false;
    public Proprietor(int id, String name, Date birthDate, String commune, String wilaya, String prenom_pere, String nom_mere, String nationnalite, String adresse_domicile, String phone) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.commune = commune;
        this.wilaya = wilaya;
        this.prenom_pere = prenom_pere;
        this.nom_mere = nom_mere;
        this.nationnalite = nationnalite;
        this.adresse_domicile = adresse_domicile;
        this.phone = phone;
    }
    
    

    public Proprietor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getWilaya() {
        return wilaya;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }

    public String getPrenom_pere() {
        return prenom_pere;
    }

    public void setPrenom_pere(String prenom_pere) {
        this.prenom_pere = prenom_pere;
    }

    public String getNom_mere() {
        return nom_mere;
    }

    public void setNom_mere(String nom_mere) {
        this.nom_mere = nom_mere;
    }

    public String getNationnalite() {
        return nationnalite;
    }

    public void setNationnalite(String nationnalite) {
        this.nationnalite = nationnalite;
    }

    public String getAdresse_domicile() {
        return adresse_domicile;
    }

    public void setAdresse_domicile(String adresse_domicile) {
        this.adresse_domicile = adresse_domicile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    
    
}
