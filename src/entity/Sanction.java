/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3lemni.entity;

import java.sql.Timestamp;

/**
 *
 * @author Amel
 */
public class Sanction {
    private int id;
    private Admin admin;
    private Eleve eleve;
    private String nature;
    private String commentaire;
    private Timestamp date_time;
    
    public Sanction(int id) {
        this.id = id;
    }
    

    public Sanction(int id, Admin admin, Eleve eleve, String nature, String commentaire, Timestamp date_time) {
        this.id = id;
        this.admin = admin;
        this.eleve = eleve;
        this.nature = nature;
        this.commentaire = commentaire;
        this.date_time = date_time;
    }

    public Sanction(Admin admin, Eleve eleve, String nature, String commentaire, Timestamp date_time) {
        this.admin = admin;
        this.eleve = eleve;
        this.nature = nature;
        this.commentaire = commentaire;
        this.date_time = date_time;
    }
    
    public Sanction(Admin admin, Eleve eleve, String nature, String commentaire) {
        this.admin = admin;
        this.eleve = eleve;
        this.nature = nature;
        this.commentaire = commentaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }
    
    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
    public Timestamp getDateTime() {
        return date_time;
    }

    public void setDate(Timestamp date_time) {
        this.date_time = date_time;
    }

    @Override
    public String toString() {
        return "Sanction{" + "id = " + id + ", admin = " + admin.getId() + ", eleve = " + eleve.getId() + ", nature = " + nature + ", commentaire = " + commentaire + ", date_time = " + date_time + '}';
    }
    
}
