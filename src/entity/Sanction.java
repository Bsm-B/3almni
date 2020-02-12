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
public class Sanction {
    private int id;
    private Admin admin;
    private Eleve eleve;
    private String nature;
    private String commentaire;
    
    public Sanction(int id) {
        this.id = id;
    }
    

    public Sanction(int id, Admin admin, Eleve eleve, String nature, String commentaire) {
        this.id = id;
        this.admin = admin;
        this.eleve = eleve;
        this.nature = nature;
        this.commentaire = commentaire;
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

    @Override
    public String toString() {
        return "Sanction{" + "id = " + id + ", admin = " + admin.getNom() + ", eleve = " + eleve.getNom() + ", nature = " + nature + ", commentaire = " + commentaire + '}';
    }
    
}
