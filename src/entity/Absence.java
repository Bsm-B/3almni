/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3lemni.entity;

import static java.sql.JDBCType.NULL;
import java.sql.Timestamp;

/**
 *
 * @author Amel
 */
public class Absence {
    private int id;
    private Matiere matiere;
    private Classe classe;
    private Timestamp date_time;
    private Eleve eleve;
    private Employe employe;
    
    public Absence(int id) {
        this.id = id;
    }
    

    public Absence(int id, Matiere matiere, Classe classe, Timestamp date_time, Eleve eleve) {
        this.id = id;
        this.matiere = matiere;
        this.classe = classe;
        this.date_time = date_time;
        this.eleve = eleve;
    }
    
    public Absence(int id, Timestamp date_time, Employe employe) {
        this.id = id;
        this.date_time = date_time;
        this.employe = employe;
    }
    
    public Absence(Matiere matiere, Classe classe, Timestamp date_time, Eleve eleve) {
        this.matiere = matiere;
        this.classe = classe;
        this.date_time = date_time;
        this.eleve = eleve;
    }

    public Absence(Timestamp date_time, Employe employe) {
        this.date_time = date_time;
        this.employe = employe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
    
    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    public Timestamp getDateTime() {
        return date_time;
    }

    /**
     *
     * @param date_time
     */
    public void setDateTime(Timestamp date_time) {
        this.date_time = date_time;
    }
    
    public Eleve getEleve() {
        return eleve;
    }

    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }
    
    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe eleve) {
        this.employe = employe;
    }
    

    /*
    @Override
    public String toString() {
        if (eleve.getId()!=0){
            return "Absence{" + "id = " + id + ", id_matiere = " + matiere.getId() + ", id_classe = " + classe.getId() + ", date_time = " + date_time + ", id_eleve = " + eleve.getId() + "}";
        }
        if (employe.getId()!=0){
            return "Absence{" + "id = " + id + ", date_time = " + date_time + ", id_employe = " + employe.getId() + "}";
        }
        return "";
    
}*/
}
