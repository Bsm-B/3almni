/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author Amel
 */

import entity.Absence;
import entity.Eleve;
import entity.Admin;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.sql.PreparedStatement;
import utils.DataSource;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import IService.IServiceAbsence;

/**
 *
 * @author House
 */
public class AbsenceService implements IServiceAbsence<Absence, Eleve> {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet rs ;

    public AbsenceService() {
        cnx = DataSource.getInstance().getCnx();
        

    }
    

    @Override
    public void create(Absence a, Eleve e) throws SQLException {
        String req = "insert into absence (matiere, date, heure, nom_eleve) values (?,?,?,?);";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1,a.getMatiere());
            pst.setString(2,a.getDate());
            pst.setString(3,a.getHeure());
            pst.setString(4,e.getNom());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    @Override
    public void delete(Absence a)  {
        try {
            String req = " delete from absence where id_absence= ?" ;
            pst = cnx.prepareStatement(req);
            pst.setInt(1, a.getId());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Absence a){
        try {
            String req = " update absence set matiere=? , date=? , heure=? , nom_eleve=?   where id_absence='"+a.getId()+"'"  ;
            ste = cnx.prepareStatement(req);
            pst.setString(1,a.getMatiere());
            pst.setString(2,a.getDate());
            pst.setString(3,a.getHeure());
            pst.setString(4,a.getEleve().getNom());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Absence> readAll() throws SQLException {
    List<Absence> list=new ArrayList<>();
    ste=cnx.createStatement();
    String req = "select * from absence";
    rs=ste.executeQuery(req);
     while (rs.next()) {                
               int id=rs.getInt(1);
               String matiere=rs.getString(2);
               String date=rs.getString(3);
               String heure=rs.getString(4);
               Eleve E = new Eleve(rs.getString(5));
               Absence a=new Absence(id, matiere, date, heure, E);
     list.add(a);
     }
    return list;
    }
    
    @Override
    public List<Absence> sort() throws SQLException {
    List<Absence> list=new ArrayList<>();
    ste=cnx.createStatement();
    String req = "select * from absence order by nom_eleve";
    rs=ste.executeQuery(req);
     while (rs.next()) {                
               int id=rs.getInt(1);
               String matiere=rs.getString(2);
               String date=rs.getString(3);
               String heure=rs.getString(4);
               Eleve E = new Eleve(rs.getString(5));
               Absence a=new Absence(id, matiere, date, heure, E);
               list.add(a);
     }
    return list;
    }

    @Override
    public List<Absence> searchById(int id) throws SQLException {
        List<Absence> list=new ArrayList<>();
        String req = " select* from absence  where id_absence='"+id+"'" ;
        try {
            ste = cnx.createStatement();
            rs=ste.executeQuery(req);
            if (rs.next())
            {
               String matiere=rs.getString(2);
               String date=rs.getString(3);
               String heure=rs.getString(4);
               Eleve E = new Eleve(rs.getString(5));
               Absence a=new Absence(id, matiere, date, heure, E);
               list.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
        
    }
  
    @Override
    public List<Absence> searchByName(String nom) throws SQLException {
        List<Absence> list=new ArrayList<>();
         String req = " select* from absence  where (nom_eleve like '"+nom+"%')" ;
        
           
            ste = cnx.createStatement();
            rs=ste.executeQuery(req);
            if (rs.next())
            {
               int id=rs.getInt(1);
               String matiere=rs.getString(2);
               String date=rs.getString(3);
               String heure=rs.getString(4);
               Eleve E = new Eleve(rs.getString(5));
               Absence a=new Absence(id, matiere, date, heure, E);
               list.add(a);
        
        
        }
        return list ;
    }

}