/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Olympians.Service;

import com.Olympians.Entite.Classroom;
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
public class ServiceClassroom implements IService<Classroom> {

    private Connection con;
    private Statement ste;

    public ServiceClassroom() {
        con = DataBase.getInstance().getConnection();

    }

    @Override
    public void ajouter(Classroom c) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `Olympians`.`Classroom` (`id`, `nom`, `type`, `capacite`) VALUES (NULL, '" + c.getNom() + "', '" + c.getType() + "', '" + c.getCapacite() + "');";
        ste.executeUpdate(requeteInsert);
    }
    public void ajouter1(Classroom c) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `Olympians`.`Classroom` ( `nom`, `type`, `capacite`) VALUES ( ?, ?, ?);");
    pre.setString(1, c.getNom());
    pre.setString(2, c.getType());
    pre.setInt(3, c.getCapacite());
    pre.executeUpdate();
    }
            

    @Override
    public boolean delete(Classroom c) throws SQLException {
        PreparedStatement pre=con.prepareStatement("DELETE FROM `Olympians`.`Classroom` WHERE `id`= ?");
        pre.setInt(1,c.getId());
    if(pre.executeUpdate()==1)
        return true;
    return false;
    }

    @Override
    public boolean update(Classroom c) throws SQLException {
       PreparedStatement pre=con.prepareStatement("UPDATE  `Olympians`.`Classroom` SET `nom`= ?, `type`= ?, `capacite`= ? WHERE `id`= ?;");
    pre.setString(1, c.getNom());
    pre.setString(2, c.getType());
    pre.setInt(3, c.getCapacite());
    pre.setInt(4, c.getId());
    if(pre.executeUpdate()==1)
        return true;
    return false;
    }

    @Override
    public List<Classroom> readAll() throws SQLException {
    List<Classroom> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from Classroom");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String type=rs.getString(3);
               int capacite=rs.getInt("capacite");
               Classroom p=new Classroom(id, nom, type, capacite);
     arr.add(p);
     }
    return arr;
    }
    
    public List<Classroom> displayClause(String cl) throws SQLException {
        String requeteInsert = "Select * from Classroom "+cl+";";
        List<Classroom> clss=new ArrayList<>();
        ste = con.prepareStatement(requeteInsert);
        ResultSet rs=ste.executeQuery(requeteInsert);
        while(rs.next())
        {
            clss.add(new Classroom(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
            System.out.println(clss.isEmpty());
        }
        return clss;
    }
}
