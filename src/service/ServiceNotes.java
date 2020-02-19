/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Olympians.Service;


import com.Olympians.Entite.Matiers;
import com.Olympians.Entite.Notes;
import com.Olympians.IService.IService;
import database.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/*
 *
 * @author Bsm
 */


public class ServiceNotes implements IService<Notes>{
    private Connection con;
    private Statement ste;

    public ServiceNotes() {
     
        con = connect.getInstance().getConnection();

    }
        
    @Override   
    public void ajouter(Notes a) throws SQLException {
        PreparedStatement PS = con.prepareStatement("INSERT INTO `notes` (`formule`, `idclasse`, `idmatiere`, `idetudent`, `idprof`, `note`, `remarque`) VALUES ( ?, ?, ?, ?, ?, ?, ?);");

        PS.setString(1,a.getFormule());
        PS.setInt(2, a.getId_class());
        PS.setInt(3, a.getId_matiere());
        PS.setInt(4, a.getId_etudent());
        PS.setInt(5, a.getId_prof());
        PS.setDouble(6, a.getNote());
        PS.setString(7, a.getRemarque());
        
        PS.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `notes` WHERE `id`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }

    @Override
    public void update(Notes a,int id) throws SQLException {
        PreparedStatement PS=con.prepareStatement("UPDATE `notes` SET `formule`=?,`idclasse`=? , `idmatiere`=? , `idetudent`=? ,`idprof`=? ,`note`=? ,`remarque`=? WHERE `id`=?");
        PS.setString(1,a.getFormule());
        PS.setInt(2, a.getId_class());
        PS.setInt(3, a.getId_matiere());
        PS.setInt(4, a.getId_etudent());
        PS.setInt(5, a.getId_prof());
        PS.setDouble(6, a.getNote());
        PS.setString(7, a.getRemarque());
        PS.setInt(8,id);
        PS.executeUpdate();
    }

    @Override
    public List<Notes> readAll() throws SQLException {
        List<Notes> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from notes");
        while (rs.next()) {
            
            int id = rs.getInt("id");
            String formule = rs.getString(2);
            int id_class = rs.getInt(3);
            int id_matiere = rs.getInt(4);
            int id_etudent = rs.getInt(5);
            int id_prof = rs.getInt(6);
            double note = rs.getDouble(7);
            String remarque = rs.getString(8);
            
            Notes a = new Notes(id,note,formule,remarque,id_class,id_matiere,id_etudent,id_prof);
           
            AL.add(a);
        }
        return AL;
    }
    
     
    public List<Notes> SearchByClass(int id_cls) throws SQLException {
        List<Notes> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM `notes` WHERE idclasse ="+id_cls);
        while (rs.next()) {
            
            int id = rs.getInt(1);
            String formule = rs.getString(2);
            int id_class = rs.getInt(3);
            int id_matiere = rs.getInt(4);
            int id_etudent = rs.getInt(5);
            int id_prof = rs.getInt(6);
            double note = rs.getDouble(7);
            String remarque = rs.getString(8);
            
            Notes a = new Notes(id,note,formule,remarque,id_class,id_matiere,id_etudent,id_prof);
           
            AL.add(a);
        }
        return AL;
    
    }

          
                   
     
     }
     
     
     
     
     
    
    
    
    

