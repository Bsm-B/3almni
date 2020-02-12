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

import entity.Sanction;
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
import IService.IServiceSanction;

/**
 *
 * @author House
 */
public class SanctionService implements IServiceSanction<Sanction, Eleve, Admin> {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet rs ;

    public SanctionService() {
        cnx = DataSource.getInstance().getCnx();
        

    }
    

    @Override
    public void create(Sanction s, Eleve e,Admin a) throws SQLException {
        String req = "insert into sanction (nom_admin, nom_eleve, nature, commentaire) values (?,?,?,?);";
        try {
            pst = cnx.prepareStatement(req);
            pst.setString(1,a.getNom());
            pst.setString(2,e.getNom());
            pst.setString(3,s.getNature());
            pst.setString(4,s.getCommentaire());
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(SanctionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            
    @Override
    public void delete(Sanction s)  {
        try {
            String req = " delete from sanction where id_sanction= ?" ;
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
            String req = " update sanction set nom_admin=? , nom_eleve=? , nature=? , commentaire=?   where id_sanction='"+s.getId()+"'"  ;
            ste = cnx.prepareStatement(req);
            pst.setString(1,s.getAdmin().getNom());
            pst.setString(2,s.getEleve().getNom());
            pst.setString(3,s.getNature());
            pst.setString(4,s.getCommentaire());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SanctionService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Sanction> readAll() throws SQLException {
    List<Sanction> list=new ArrayList<>();
    ste=cnx.createStatement();
    String req = "select * from sanction";
    rs=ste.executeQuery(req);
     while (rs.next()) {                
               int id=rs.getInt(1);
               String matiere=rs.getString(2);
               Admin Ad = new Admin(rs.getString(2));
               Eleve E = new Eleve(rs.getString(3));
               String neutre=rs.getString(4);
               String commentaire=rs.getString(5);
               Sanction s=new Sanction(id, Ad, E, neutre, commentaire);
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
               String matiere=rs.getString(2);
               Admin Ad = new Admin(rs.getString(2));
               Eleve E = new Eleve(rs.getString(3));
               String neutre=rs.getString(4);
               String commentaire=rs.getString(5);
               Sanction s=new Sanction(id, Ad, E, neutre, commentaire);
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
               String matiere=rs.getString(2);
               Admin Ad = new Admin(rs.getString(2));
               Eleve E = new Eleve(rs.getString(3));
               String neutre=rs.getString(4);
               String commentaire=rs.getString(5);
               Sanction s=new Sanction(id, Ad, E, neutre, commentaire);
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
         String req = " select* from sanction  where (nom_eleve like '"+nom+"%')" ;
        
           
            ste = cnx.createStatement();
            rs=ste.executeQuery(req);
            if (rs.next())
            {
               int id=rs.getInt(1);
               String matiere=rs.getString(2);
               Admin Ad = new Admin(rs.getString(2));
               Eleve E = new Eleve(rs.getString(3));
               String neutre=rs.getString(4);
               String commentaire=rs.getString(5);
               Sanction s=new Sanction(id, Ad, E, neutre, commentaire);
               list.add(s);
        
        
        
        }
        return list ;
    }

}