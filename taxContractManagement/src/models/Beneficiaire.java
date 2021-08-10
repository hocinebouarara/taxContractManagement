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
public class Beneficiaire {

    int id;
    String name;
    Date birthDate;
    String commune, wilaya, prenom_pere, nom_mere, nationnalite, adresse_domicile,
            activite_prcpl, adresse_act_prcpl, activite_sec, adresse_act_sec, n_register_comrc;
    Date date_registre_c;
    String n_cart_artisan;
    Date date_carte_ar;
    String n_agrement;
    Date date_agrement;
    String autres;
    Date date;

    public Beneficiaire(int id, String name, Date birthDate, String commune, String wilaya, String prenom_pere, String nom_mere, String nationnalite, String adresse_domicile, String activite_prcpl, String adresse_act_prcpl, String activite_sec, String adresse_act_sec, String n_register_comrc, Date date_registre_c, String n_cart_artisan, Date date_carte_ar, String n_agrement, Date date_agrement, String autres, Date date) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.commune = commune;
        this.wilaya = wilaya;
        this.prenom_pere = prenom_pere;
        this.nom_mere = nom_mere;
        this.nationnalite = nationnalite;
        this.adresse_domicile = adresse_domicile;
        this.activite_prcpl = activite_prcpl;
        this.adresse_act_prcpl = adresse_act_prcpl;
        this.activite_sec = activite_sec;
        this.adresse_act_sec = adresse_act_sec;
        this.n_register_comrc = n_register_comrc;
        this.date_registre_c = date_registre_c;
        this.n_cart_artisan = n_cart_artisan;
        this.date_carte_ar = date_carte_ar;
        this.n_agrement = n_agrement;
        this.date_agrement = date_agrement;
        this.autres = autres;
        this.date = date;
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

    public String getActivite_prcpl() {
        return activite_prcpl;
    }

    public void setActivite_prcpl(String activite_prcpl) {
        this.activite_prcpl = activite_prcpl;
    }

    public String getAdresse_act_prcpl() {
        return adresse_act_prcpl;
    }

    public void setAdresse_act_prcpl(String adresse_act_prcpl) {
        this.adresse_act_prcpl = adresse_act_prcpl;
    }

    public String getActivite_sec() {
        return activite_sec;
    }

    public void setActivite_sec(String activite_sec) {
        this.activite_sec = activite_sec;
    }

    public String getAdresse_act_sec() {
        return adresse_act_sec;
    }

    public void setAdresse_act_sec(String adresse_act_sec) {
        this.adresse_act_sec = adresse_act_sec;
    }

    public String getN_register_comrc() {
        return n_register_comrc;
    }

    public void setN_register_comrc(String n_register_comrc) {
        this.n_register_comrc = n_register_comrc;
    }

    public Date getDate_registre_c() {
        return date_registre_c;
    }

    public void setDate_registre_c(Date date_registre_c) {
        this.date_registre_c = date_registre_c;
    }

    public String getN_cart_artisan() {
        return n_cart_artisan;
    }

    public void setN_cart_artisan(String n_cart_artisan) {
        this.n_cart_artisan = n_cart_artisan;
    }

    public Date getDate_carte_ar() {
        return date_carte_ar;
    }

    public void setDate_carte_ar(Date date_carte_ar) {
        this.date_carte_ar = date_carte_ar;
    }

    public String getN_agrement() {
        return n_agrement;
    }

    public void setN_agrement(String n_agrement) {
        this.n_agrement = n_agrement;
    }

    public Date getDate_agrement() {
        return date_agrement;
    }

    public void setDate_agrement(Date date_agrement) {
        this.date_agrement = date_agrement;
    }

    public String getAutres() {
        return autres;
    }

    public void setAutres(String autres) {
        this.autres = autres;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
