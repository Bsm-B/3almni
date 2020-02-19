/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3lemni.service;

/**
 *
 * @author Amel
 */

import pkg3lemni.entity.Absence;
import pkg3lemni.entity.Eleve;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.sql.PreparedStatement;
import pkg3lemni.utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pkg3lemni.IService.IService;
import pkg3lemni.entity.Classe;
import pkg3lemni.entity.Matiere;
import java.sql.Timestamp;
import pkg3lemni.entity.Employe;

/**
 *
 * @author Amel
 */
public class AbsenceService implements IService<Absence> {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet rs ;

    public AbsenceService() {
        cnx = DataBase.getInstance().getCnx();
        

    }
    

    
    public void addEleve(Absence a, Eleve el, Matiere m, Classe c) throws SQLException {
        String req = "insert into absence (id_matiere, id_classe, date_time, id_eleve) values (?,?,?,?)";
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1,m.getId());
            pst.setInt(2,c.getId());
            pst.setTimestamp(3,a.getDateTime());
            pst.setInt(4,el.getId());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addEmploye(Absence a, Employe em) throws SQLException {
        String req = "insert into absence (date_time, id_employe) values (?,?)";
        try {
            pst = cnx.prepareStatement(req);
            pst.setTimestamp(1,a.getDateTime());
            pst.setInt(2,em.getId());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    public void deleteEleve(Absence a)  {
        try {
            String req = "delete from absence where id_absence= ?" ;
            pst = cnx.prepareStatement(req);
            pst.setInt(1, a.getId());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public void deleteEmploye(Absence a)  {
        try {
            String req = "delete from absence where id_absence= ?" ;
            pst = cnx.prepareStatement(req);
            pst.setInt(1, a.getId());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void updateEleve(Absence a, Eleve el, Matiere m, Classe c){
        try {
            String req = "update absence set id_matiere=? , id_classe=?, date_time=? , id_eleve=?   where id_absence='"+a.getId()+"'"  ;
            ste = cnx.prepareStatement(req);
            pst.setInt(2,m.getId());
            pst.setInt(3,c.getId());
            pst.setTimestamp(4,a.getDateTime());
            pst.setInt(5,el.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateEmploye(Absence a, Employe em){
        try {
            String req = "update absence set date_time=? , id_employe=?   where id_absence='"+a.getId()+"'"  ;
            ste = cnx.prepareStatement(req);
            pst.setTimestamp(2,a.getDateTime());
            pst.setInt(3,em.getId());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public List<Absence> displayEleve() throws SQLException {
    List<Absence> list=new ArrayList<>();
    ste=cnx.createStatement();
    String req = "select id_absence, id_matiere, id_classe, date_time, id_eleve from absence";
    rs=ste.executeQuery(req);
     while (rs.next()) {                
               int id=rs.getInt(1);
               Matiere M = new Matiere(rs.getInt(2));
               Classe C = new Classe(rs.getInt(3));
               Timestamp date_time = rs.getTimestamp(4);
               Eleve El = new Eleve(rs.getInt(5));
               Absence a=new Absence(id, M, C, date_time, El);
     list.add(a);
     }
    return list;
    }
    
    public List<Absence> displayEmploye() throws SQLException {
    List<Absence> list=new ArrayList<>();
    ste=cnx.createStatement();
    String req = "select id_absence, date_time, id_employe from absence";
    rs=ste.executeQuery(req);
     while (rs.next()) {                
               int id=rs.getInt(1);
               Timestamp date_time = rs.getTimestamp(2);
               Employe Em = new Employe(rs.getInt(3));
               Absence a=new Absence(id, date_time, Em);
     list.add(a);
     }
    return list;
    }
    
    
    
    @Override
    public List<Absence> sort() throws SQLException {
    List<Absence> list=new ArrayList<>();
    ste=cnx.createStatement();
    String req = "select * from absence order by id_eleve";
    rs=ste.executeQuery(req);
     while (rs.next()) {                
               int id=rs.getInt(1);
               Matiere M = new Matiere(rs.getInt(2));
               Classe C = new Classe(rs.getInt(3));
               Timestamp date_time = rs.getTimestamp(4);
               Eleve E = new Eleve(rs.getInt(5));
               Absence a=new Absence(id, M, C, date_time, E);
               list.add(a);
     }
    return list;
    }

    @Override
    public List<Absence> searchById(int id) throws SQLException {
        List<Absence> list=new ArrayList<>();
        String req = "select * from absence  where id_absence='"+id+"'" ;
        try {
            ste = cnx.createStatement();
            rs=ste.executeQuery(req);
            if (rs.next())
            {
               Matiere M = new Matiere(rs.getInt(2));
               Classe C = new Classe(rs.getInt(3));
               Timestamp date_time = rs.getTimestamp(4);
               Eleve E = new Eleve(rs.getInt(5));
               Absence a=new Absence(id, M, C, date_time, E);
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
         String req = "select* from absence  where id_eleve=(select id_eleve from eleve where nom_eleve like '%"+nom+"%')" ;
        
            ste = cnx.createStatement();
            rs=ste.executeQuery(req);
            if (rs.next())
            {
               int id=rs.getInt(1);
               Matiere M = new Matiere(rs.getInt(2));
               Classe C = new Classe(rs.getInt(3));
               Timestamp date_time = rs.getTimestamp(4);
               Eleve E = new Eleve(rs.getInt(5));
               Absence a=new Absence(id, M, C, date_time, E);
               list.add(a);
        }
        return list ;
    }

    @Override
    public void add(Absence t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Absence t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Absence t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Absence> display() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}