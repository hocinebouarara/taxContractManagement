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
public class Property {

    int id, id_propr;

    String nom;
    Date proprBirth;
    String article, titre, commune, inspection, wilaya, reu, origin_propriete, n_terrain, n_immeuble,
            n_etage, n_appartement, rez_chaussee, nbr_etage, nbr_apparemment,
            type_immbeuble;
    Float superficie_tot, superficie_batie, superficie_non_batie;
    Date date_achev;
    String usage, adresse_prcpl, acie;
    Date date;

    public Property(int id, int id_propr, String nom, Date proprBirth, String article, String titre,
            String commune, String inspection, String wilaya, String reu, String origin_propriete,
            String n_terrain, String n_immeuble, String n_etage, String n_appartement, String rez_chaussee,
            String nbr_etage, String nbr_apparemment, String type_immbeuble, Float superficie_tot,
            Float superficie_batie, Float superficie_non_batie, Date date_achev, String usage,
            String adresse_prcpl, String acie, Date date) {
        this.id = id;
        this.id_propr = id_propr;
        this.nom = nom;
        this.proprBirth = proprBirth;
        this.article = article;
        this.titre = titre;
        this.commune = commune;
        this.inspection = inspection;
        this.wilaya = wilaya;
        this.reu = reu;
        this.origin_propriete = origin_propriete;
        this.n_terrain = n_terrain;
        this.n_immeuble = n_immeuble;
        this.n_etage = n_etage;
        this.n_appartement = n_appartement;
        this.rez_chaussee = rez_chaussee;
        this.nbr_etage = nbr_etage;
        this.nbr_apparemment = nbr_apparemment;
        this.type_immbeuble = type_immbeuble;
        this.superficie_tot = superficie_tot;
        this.superficie_batie = superficie_batie;
        this.superficie_non_batie = superficie_non_batie;
        this.date_achev = date_achev;
        this.usage = usage;
        this.adresse_prcpl = adresse_prcpl;
        this.acie = acie;
        this.date = date;
    }

    public Property() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_propr() {
        return id_propr;
    }

    public void setId_propr(int id_propr) {
        this.id_propr = id_propr;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getProprBirth() {
        return proprBirth;
    }

    public void setProprBirth(Date proprBirth) {
        this.proprBirth = proprBirth;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getInspection() {
        return inspection;
    }

    public void setInspection(String inspection) {
        this.inspection = inspection;
    }

    public String getWilaya() {
        return wilaya;
    }

    public void setWilaya(String wilaya) {
        this.wilaya = wilaya;
    }

    public String getReu() {
        return reu;
    }

    public void setReu(String reu) {
        this.reu = reu;
    }

    public String getOrigin_propriete() {
        return origin_propriete;
    }

    public void setOrigin_propriete(String origin_propriete) {
        this.origin_propriete = origin_propriete;
    }

    public String getN_terrain() {
        return n_terrain;
    }

    public void setN_terrain(String n_terrain) {
        this.n_terrain = n_terrain;
    }

    public String getN_immeuble() {
        return n_immeuble;
    }

    public void setN_immeuble(String n_immeuble) {
        this.n_immeuble = n_immeuble;
    }

    public String getN_etage() {
        return n_etage;
    }

    public void setN_etage(String n_etage) {
        this.n_etage = n_etage;
    }

    public String getN_appartement() {
        return n_appartement;
    }

    public void setN_appartement(String n_appartement) {
        this.n_appartement = n_appartement;
    }

    public String getRez_chaussee() {
        return rez_chaussee;
    }

    public void setRez_chaussee(String rez_chaussee) {
        this.rez_chaussee = rez_chaussee;
    }

    public String getNbr_etage() {
        return nbr_etage;
    }

    public void setNbr_etage(String nbr_etage) {
        this.nbr_etage = nbr_etage;
    }

    public String getNbr_apparemment() {
        return nbr_apparemment;
    }

    public void setNbr_apparemment(String nbr_apparemment) {
        this.nbr_apparemment = nbr_apparemment;
    }

    public String getType_immbeuble() {
        return type_immbeuble;
    }

    public void setType_immbeuble(String type_immbeuble) {
        this.type_immbeuble = type_immbeuble;
    }

    public Float getSuperficie_tot() {
        return superficie_tot;
    }

    public void setSuperficie_tot(Float superficie_tot) {
        this.superficie_tot = superficie_tot;
    }

    public Float getSuperficie_batie() {
        return superficie_batie;
    }

    public void setSuperficie_batie(Float superficie_batie) {
        this.superficie_batie = superficie_batie;
    }

    public Float getSuperficie_non_batie() {
        return superficie_non_batie;
    }

    public void setSuperficie_non_batie(Float superficie_non_batie) {
        this.superficie_non_batie = superficie_non_batie;
    }

    public Date getDate_achev() {
        return date_achev;
    }

    public void setDate_achev(Date date_achev) {
        this.date_achev = date_achev;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getAdresse_prcpl() {
        return adresse_prcpl;
    }

    public void setAdresse_prcpl(String adresse_prcpl) {
        this.adresse_prcpl = adresse_prcpl;
    }

    public String getAcie() {
        return acie;
    }

    public void setAcie(String acie) {
        this.acie = acie;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
