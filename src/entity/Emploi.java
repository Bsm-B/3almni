/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.pkg1;

import java.time.Year;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author soltani
 */
public class Emploi {
    
     private int id; 
     private java.sql.Date week;
     private String url;
     private String classe;
     private String year;

    public Emploi(int id ,java.sql.Date week, String url, String classe, String year) {
        this.id= id;
        this.week = week;
        this.url = url;
        this.classe = classe;
        this.year = year;
    }

    @Override
    public String toString() {
        return "Emploi{" + "id=" + id + ", week=" + week + ", url=" + url + ", classe=" + classe + ", year=" + year + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        hash = 83 * hash + Objects.hashCode(this.week);
        hash = 83 * hash + Objects.hashCode(this.url);
        hash = 83 * hash + Objects.hashCode(this.classe);
        hash = 83 * hash + Objects.hashCode(this.year);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Emploi other = (Emploi) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        if (!Objects.equals(this.classe, other.classe)) {
            return false;
        }
        if (!Objects.equals(this.week, other.week)) {
            return false;
        }
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        return true;
    }
    
    public Emploi(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.sql.Date getWeek() {
        return week;
    }

    public void setWeek(java.sql.Date week) {
        this.week = week;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

   /* public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }*/
    
    
}
