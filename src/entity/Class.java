/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Olympians.Entite;

/**
 *
 * @author House
 */
public class Class {
    private int id;
    private String nom;
    private int capacite;
    private String branche;
    private String annee_scholaire;

    public Class(int id, String nom, String branche, int capacite,String annee_scholaire) {
        this.id = id;
        this.nom = nom;
        this.branche = branche;
        this.capacite = capacite;
        this.annee_scholaire=annee_scholaire;
    }

    public Class(String nom, String branche, int capacite, String annee_scholaire) {
        this.nom = nom;
        this.branche = branche;
        this.capacite = capacite;
        this.annee_scholaire=annee_scholaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getBranche() {
        return branche;
    }

    public void setBranche(String branche) {
        this.branche = branche;
    }
    
    public String getAnnee_Scholaire() {
        return annee_scholaire;
    }

    public void setAnnee_Scholaire(String annee_scholaire) {
        this.annee_scholaire = annee_scholaire;
    }
    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return "Classe{" + "id=" + id + ", nom=" + nom + ", branche=" + branche + ", capacite=" + capacite +", annee_scholaire="+ annee_scholaire + '}';
    }
    
}
