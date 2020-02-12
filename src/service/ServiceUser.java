/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.Service;

import java.sql.SQLException;
import java.util.List;
import pidev.Entite.User;
import pidev.IService.IService;
import pidev.DataBase.DataBase;

import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServiceUser implements IService<User>{
    
    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public ServiceUser() {
        con = DataBase.getInstance().getConnection();
    }
    
    
    @Override
    public void ajouter(User a) throws SQLException {
        PreparedStatement PS = con.prepareStatement("INSERT INTO `3lemni`.`User` (`Nom_User`,`Prenom_User`, `Username_User`, `Email_User`, `Pwd_User`, `Date_Naiss_User`, `Locked_User`, `Expired_User`, `Role_User`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
        PS.setString(1, a.getNom());
        PS.setString(2, a.getPrenom());
        PS.setString(3,a.getUsername());
        PS.setString(4,a.getEmail());
        PS.setString(5, a.getPwd());
        PS.setString(6, a.getDatenaiss());
        PS.setInt(7, a.getLocked());
        PS.setInt(8, a.getExpired());
        PS.setString(9, a.getRole());
        PS.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException {
        PreparedStatement PS = con.prepareStatement("DELETE FROM `3lemni`.`User` WHERE `Id_User`=?");
        PS.setInt(1,id);
        PS.executeUpdate();
    }

    @Override
    public void update(User a,int id) throws SQLException {
        PreparedStatement PS=con.prepareStatement("UPDATE `3lemni`.`User` SET `Nom_User`=?,`Prenom_User`=? ,`Username_User`=?,`Email_User`=?,`Pwd_User`=?,`Date_Naiss_User`=?,`Locked_User`=?,`Expired_User`=?,`Role_User`=? WHERE `Id_User`=?");
        PS.setString(1, a.getNom());
        PS.setString(2, a.getPrenom());
        PS.setString(3,a.getUsername());
        PS.setString(4,a.getEmail());
        PS.setString(5, a.getPwd());
        PS.setString(6, a.getDatenaiss());
        PS.setInt(7, a.getLocked());
        PS.setInt(8, a.getExpired());
        PS.setString(9, a.getRole());
        PS.setInt(10,id);
        PS.executeUpdate();
    }

    @Override
    public List<User> readAll() throws SQLException {
        List<User> AL = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from User");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("Nom_User");
            String prenom = rs.getString("Prenom_User");
            String username = rs.getString("Username_User");
            String email = rs.getString("Pwd_User");
            String pwd = rs.getString("Pwd_User");
            String datenaiss = rs.getString("Date_Naiss_User");
            int locked = rs.getInt(8);
            int expired = rs.getInt(9);
            String role = rs.getString("Role_User");
            User a = new User(id,nom,prenom,username,email,pwd,datenaiss,locked,expired,role );
            AL.add(a);
        }
        return AL;
    }
    
    public List<User> getTrier() throws SQLException {
    List<User> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from User ORDER BY Nom_User DESC");
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString("Nom_User");
            String prenom = rs.getString("Prenom_User");
            String username = rs.getString("Username_User");
            String email = rs.getString("Pwd_User");
            String pwd = rs.getString("Pwd_User");
            String datenaiss = rs.getString("Date_Naiss_User");
            int locked = rs.getInt(8);
            int expired = rs.getInt(9);
            String role = rs.getString("Role_User");
            User a = new User(id,nom,prenom,username,email,pwd,datenaiss,locked,expired,role );
            arr.add(a);
     }
    return arr;
    }

   public User getById(int id) {
          User a = null;
         String requete = " select* from User  where Id_User='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new User(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getInt(8),res.getInt(9),res.getString(10));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
  public User getByName(String n) {
          User a = null;
         String requete = " select* from User  where (Username_User like '%"+n+"%')" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new User(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getInt(8),res.getInt(9),res.getString(10));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
   
}

