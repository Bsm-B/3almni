/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.pkg1;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author soltani
 */
public class ServiceEmploi {
    
    
    private Connection con;
    private PreparedStatement ste;
    java.util.Date date=new java.util.Date();
    

    
    
    
    

    public ServiceEmploi() throws SQLException {
        con = DataBase.getInstance().getConnection();
    }
    
    
    public void add(Emploi e) throws SQLException {
        System.out.println(date);
        String requeteInsert = "INSERT INTO `Emploi` (`url`, `class`,`date`) VALUES(?,?,?);";
        ste = con.prepareStatement(requeteInsert);
        ste.setString(1, e.getUrl());
        ste.setString(2, e.getClasse());
        ste.setDate(3, e.getWeek());
        ste.executeUpdate();
    }
    
    
    public void display() throws SQLException {
        String requeteInsert = "Select * from Emploi;";
       
        List<Emploi> emp=new ArrayList<>();
        ste = con.prepareStatement(requeteInsert);
        ResultSet rs=ste.executeQuery(requeteInsert);
        while(rs.next())
        {
            
            System.out.println("from "+rs.getDate(4).toLocalDate().plusWeeks(0)+" to : "+rs.getDate(4).toLocalDate().plusWeeks(1));
   
            emp.add(new Emploi(rs.getInt(1),rs.getDate(4),rs.getString(2),rs.getString(3),rs.getString(4)));
            System.out.println(emp.isEmpty());
        }
    }
    
    
    public List<Emploi> displayClause(String cl) throws SQLException {
        String requeteInsert = "Select * from Emploi "+cl+";";
        List<Emploi> emp=new ArrayList<>();
        ste = con.prepareStatement(requeteInsert);
        ResultSet rs=ste.executeQuery(requeteInsert);
        while(rs.next())
        {
            emp.add(new Emploi(rs.getInt(1),rs.getDate(4),rs.getString(2),rs.getString(3),rs.getString(4)));
            System.out.println(emp.isEmpty());
        }
        return emp;
        
    }
    
    
    
    public void update(Emploi e) throws SQLException {
        String requeteInsert = "update `Emploi` set   url = ? , class = ? , date= ?  where id = ?;";
        ste = con.prepareStatement(requeteInsert);
        ste.setString(1, e.getUrl());
        ste.setString(2, e.getClasse());
        ste.setDate(3, e.getWeek());
        ste.setInt(4, e.getId());
        ste.executeUpdate();
        System.out.println("Updated");
    }
   
    public void delete(int id) throws SQLException {
        String requeteInsert = "Delete from `Emploi` where id = (?) ;";
        ste = con.prepareStatement(requeteInsert);
        ste.setInt(1, id);
        ste.execute();
    }
    
}


