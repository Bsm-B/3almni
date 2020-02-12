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
public class Classroom {
    private int id;
    private String nom;
    private String type;
    private int capacite;

    public Classroom(int id, String nom, String type, int capacite) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.capacite = capacite;
    }

    public Classroom(String nom, String type, int capacite) {
        this.nom = nom;
        this.type = type;
        this.capacite = capacite;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    @Override
    public String toString() {
        return "Classes{" + "id=" + id + ", nom=" + nom + ", type=" + type + ", capacite=" + capacite + '}';
    }
    
}
