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

import pkg3lemni.entity.Sanction;
import pkg3lemni.entity.Eleve;
import pkg3lemni.entity.Admin;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.sql.PreparedStatement;
import pkg3lemni.utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pkg3lemni.IService.IService;
import java.sql.Timestamp;

/**
 *
 * @author Amel
 */


public class SanctionService implements IService<Sanction> {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet rs ;

    public SanctionService() {
        cnx = DataBase.getInstance().getCnx();
        

    }
    

    @Override
    public void add(Sanction s) throws SQLException {
        String req = "insert into sanction (id_admin, id_eleve, nature, commentaire, date_time) values (?,?,?,?,?);";
        try {
            pst = cnx.prepareStatement(req);
            pst.setInt(1,s.getAdmin().getId());
            pst.setInt(2,s.getEleve().getId());
            pst.setString(3,s.getNature());
            pst.setString(4,s.getCommentaire());
            pst.setTimestamp(5,s.getDateTime());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SanctionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    @Override
    public void delete(Sanction s)  {
        try {
            String req = "delete from sanction where id_sanction= ?" ;
            pst = cnx.prepareStatement(req);
            pst.setInt(1, s.getId());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SanctionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Sanction s){
        try {
            String req = "update sanction set nom_admin=? , nom_eleve=? , nature=? , commentaire=?   where id_sanction='"+s.getId()+"'"  ;
            ste = cnx.prepareStatement(req);
            pst.setInt(1,s.getAdmin().getId());
            pst.setInt(2,s.getEleve().getId());
            pst.setString(3,s.getNature());
            pst.setString(4,s.getCommentaire());
            pst.setTimestamp(5,s.getDateTime());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SanctionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Sanction> display() throws SQLException {
    List<Sanction> list=new ArrayList<>();
    ste=cnx.createStatement();
    String req = "select * from sanction";
    rs=ste.executeQuery(req);
     while (rs.next()) {                
               int id=rs.getInt(1);
               Admin Ad = new Admin(rs.getInt(2));
               Eleve E = new Eleve(rs.getInt(3));
               String nature=rs.getString(4);
               String commentaire=rs.getString(5);
               Timestamp date_time = new Timestamp(System.currentTimeMillis());
               Sanction s=new Sanction(id, Ad, E, nature, commentaire, date_time);
               list.add(s);
     }
    return list;
    }
    
    @Override
    public List<Sanction> sort() throws SQLException {
    List<Sanction> list=new ArrayList<>();
    ste=cnx.createStatement();
    String req = "select * from sanction order by nom_eleve";
    rs=ste.executeQuery(req);
     while (rs.next()) {                
               int id=rs.getInt(1);
               Admin Ad = new Admin(rs.getInt(2));
               Eleve E = new Eleve(rs.getInt(3));
               String nature=rs.getString(4);
               String commentaire=rs.getString(5);
               Timestamp date_time = rs.getTimestamp(6);
               Sanction s=new Sanction(id, Ad, E, nature, commentaire, date_time);
               list.add(s);
     }
    return list;
    }

    @Override
    public List<Sanction> searchById(int id) throws SQLException {
        List<Sanction> list=new ArrayList<>();
        String req = " select* from sanction  where id_sanction='"+id+"'" ;
        try {
            ste = cnx.createStatement();
            rs=ste.executeQuery(req);
            if (rs.next())
            {
               Admin Ad = new Admin(rs.getInt(2));
               Eleve E = new Eleve(rs.getInt(3));
               String nature=rs.getString(4);
               String commentaire=rs.getString(5);
               Timestamp date_time = rs.getTimestamp(6);
               Sanction s=new Sanction(id, Ad, E, nature, commentaire, date_time);
               list.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanctionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list ;
        
    }
  
    @Override
    public List<Sanction> searchByName(String nom) throws SQLException {
        List<Sanction> list=new ArrayList<>();
         String req = "select * from sanction  where id_eleve=(select id_eleve from eleve where nom_eleve like '%"+nom+"%')" ;
        
            ste = cnx.createStatement();
            rs=ste.executeQuery(req);
            if (rs.next())
            {
               int id=rs.getInt(1);
               Admin Ad = new Admin(rs.getInt(2));
               Eleve E = new Eleve(rs.getInt(3));
               String nature=rs.getString(4);
               String commentaire=rs.getString(5);
               Timestamp date_time = rs.getTimestamp(6);
               Sanction s=new Sanction(id, Ad, E, nature, commentaire, date_time);
               list.add(s);
        }
        return list ;
    }
    
    

}