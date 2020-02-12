/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entite;

/**
 *
 * @author Bsm
 */
public class Notes {
    
    int id;
    double note;
    String formule;
    String remarque;
    int id_class;
    int id_matiere;
    int id_etudent;
    int id_prof;

    public Notes(int id, double note, String formule, String remarque, int id_class, int id_matiere, int id_etudent, int id_prof) {
        this.id = id;
        this.note = note;
        this.formule = formule;
        this.remarque = remarque;
        this.id_class = id_class;
        this.id_matiere = id_matiere;
        this.id_etudent = id_etudent;
        this.id_prof = id_prof;
    }

    public Notes(double note, String formule, String remarque, int id_class, int id_matiere, int id_etudent, int id_prof) {
        this.note = note;
        this.formule = formule;
        this.remarque = remarque;
        this.id_class = id_class;
        this.id_matiere = id_matiere;
        this.id_etudent = id_etudent;
        this.id_prof = id_prof;
    }

   
    
    
    public String getRemarque() {
        return remarque;
    }

    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }


    
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getFormule() {
        return formule;
    }

    public void setFormule(String formule) {
        this.formule = formule;
    }

    public int getId_class() {
        return id_class;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }

    public int getId_matiere() {
        return id_matiere;
    }

    public void setId_matiere(int id_matiere) {
        this.id_matiere = id_matiere;
    }

    public int getId_etudent() {
        return id_etudent;
    }

    public void setId_etudent(int id_etudent) {
        this.id_etudent = id_etudent;
    }

    public int getId_prof() {
        return id_prof;
    }

    public void setId_prof(int id_prof) {
        this.id_prof = id_prof;
    }
    
    
       
    @Override
    public  String toString(){
        return "Note -> Id :" + id + " Note :" + note + "Formule  :"+ formule + " class :" + id_class +
              "prof :" + id_prof + "etudent :" + id_etudent + "matiere :"+ id_matiere ; 
              
    }
    
    
    
}
