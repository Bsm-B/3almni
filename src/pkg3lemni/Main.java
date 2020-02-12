/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3lemni;

import entity.Absence;
import entity.Sanction;
import entity.Eleve;
import entity.Admin;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.AbsenceService;
import service.SanctionService;
import utils.DataSource;


/**
 *
 * @author Amel
 */

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            DataSource ds = DataSource.getInstance();
            // System.out.println(ds);
            
            System.out.println("ok");
            
            Eleve e1=new Eleve(1,"Oscar");
            Eleve e2=new Eleve(1,"Khalifa");
            Admin ad=new Admin(1,"Monsieur");
            Absence a1=new Absence("math", "11/11/11", "11:00", e1);
            Absence a2=new Absence("phy", "12/12/12", "12:00", e2);
            Absence a3 = new Absence(2);
            AbsenceService as = new AbsenceService();
            Sanction s1=new Sanction(ad, e1, "expulsion", "impoli");
            Sanction s2=new Sanction(ad, e2, "alerte", "bruit");
            Sanction s3 = new Sanction(3);
            SanctionService ss = new SanctionService();
            
            
            
            //as.create(a2,e2);
            //ss.create(s1,e2,ad);
            
        
            //as.delete(a3);
            //ss.delete(s3);
            
            //as.readAll().forEach(System.out::println);
            //ss.readAll().forEach(System.out::println);
            
            //as.sort().forEach(System.out::println);
            //ss.sort().forEach(System.out::println);
            
            //as.searchById(1).forEach(System.out::println);
            ss.searchById(2).forEach(System.out::println);
            
            //as.searchByName("Oscar").forEach(System.out::println);
            ss.searchByName("Khalifa").forEach(System.out::println);
            
            
            // TODO code application logic here
        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}