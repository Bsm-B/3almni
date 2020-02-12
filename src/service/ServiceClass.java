/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Olympians.Service;

import com.Olympians.Entite.Class;
import com.Olympians.IService.IService;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import com.Olympians.Utils.DataBase;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author House
 */
public class ServiceClass implements IService<Class> {

    private Connection con;
    private Statement ste;

    public ServiceClass() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(Class c) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `Olympians`.`Class` (`id`, `nom`, `branche`, `capacite`, `annee_scholaire) VALUES (NULL, '" + c.getNom() + "', '" + c.getBranche() + "', '" + c.getCapacite() + "', '"+c.getAnnee_Scholaire()+"');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(Class c) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `Olympians`.`Class` ( `nom`, `branche`, `capacite`, `annee_scholaire) VALUES ( ?, ?, ?, ?);");
    pre.setString(1, c.getNom());
    pre.setString(2, c.getBranche());
    pre.setInt(3, c.getCapacite());
    pre.setString(4, c.getAnnee_Scholaire());
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(Class c) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM `Olympians`.`Class` WHERE `id`= ?");
        pre.setInt(1,c.getId());
    if(pre.executeUpdate()==1)
        return true;
    return false;
    }

    @Override
    public boolean update(Class c) throws SQLException {
       PreparedStatement pre=con.prepareStatement("UPDATE  `Olympians`.`Class` SET `nom`= ?, `branche`= ?, `capacite`= ?, `annee_scholaire`= ? WHERE `id`= ?;");
    pre.setString(1, c.getNom());
    pre.setString(2, c.getBranche());
    pre.setInt(3, c.getCapacite());
    pre.setString(4, c.getAnnee_Scholaire());
    pre.setInt(5, c.getId());
    if(pre.executeUpdate()==1)
        return true;
    return false;
    }

    @Override
    public List<Class> readAll() throws SQLException {
    List<Class> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Class");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String branche=rs.getString("branche");
               int capacite=rs.getInt("capacite");
               String annee_scholaire=rs.getString("annee_scholaire");
               Class p=new Class(id, nom, branche, capacite,annee_scholaire);
     arr.add(p);
     }
    return arr;
    }
    
    public List<Class> displayClause(String cl) throws SQLException {
        String requeteInsert = "Select * from Class "+cl+";";
        List<Class> clss=new ArrayList<>();
        ste = con.prepareStatement(requeteInsert);
        ResultSet rs=ste.executeQuery(requeteInsert);
        while(rs.next())
        {
            clss.add(new Class(rs.getInt("id"),rs.getString("nom"),rs.getString("branche"),rs.getInt("capacite"),rs.getString("annee_scholaire")));
            System.out.println(clss.isEmpty());
        }
        return clss;
    }
}
