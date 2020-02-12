/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Amel
 */
public class Absence {
    private int id;
    private String matiere;
    private String date;
    private String heure;
    private Eleve eleve;
    
    public Absence(int id) {
        this.id = id;
    }
    

    public Absence(int id, String matiere, String date, String heure, Eleve eleve) {
        this.id = id;
        this.matiere = matiere;
        this.date = date;
        this.heure = heure;
        this.eleve = eleve;
    }

    public Absence(String matiere, String date, String heure, Eleve eleve) {
        this.matiere = matiere;
        this.date = date;
        this.heure = heure;
        this.eleve = eleve;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }
    
    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    @Override
    public String toString() {
        return "Absence{" + "id = " + id + ", matiere = " + matiere + ", date = " + date + ", heure = " + heure + ", eleve = " + eleve.getNom() + '}';
    }
    
}
