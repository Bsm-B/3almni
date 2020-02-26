/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Olympians.Service;

import com.Olympians.Entite.Clubs;
import com.Olympians.Entite.User;
import com.Olympians.IService.IService;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.sql.PreparedStatement;
import com.Olympians.Utils.DataBase;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.itextpdf.text.BaseColor;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 *
 * @author House
 */
public class ServiceClubs implements IService<Clubs> {

    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public ServiceClubs() {
        con = DataBase.getInstance().getConnection();

    }

    public void ajouter(Clubs t,User u) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO club (`id`, `nom`, `categorie`, `description`, `date_fondation`, `nbmembres`, `responsable`) VALUES (NULL, '" + t.getNom() + "', '" + t.getCategorie() + "', '" + t.getDescription() + "', '" + t.getDatefondation() + "', '" + t.getNbmembres() + "', '" + u.getResponsable() + "');";
        try {
            ste=con.createStatement();
            ste.executeUpdate(requeteInsert);
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClubs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   /* public void ajouter1(Clubs p) throws SQLException
    {
    PreparedStatement pre=con.prepareStatement("INSERT INTO `gestion_des_ecoles`.`club` ( `nom`, `categorie`, `descrition`) VALUES ( ?, ?, ?);");
    pre.setString(1, p.getNom());
    pre.setString(2, p.getPrenom());
    pre.setInt(3, p.getAge());
    pre.executeUpdate();
    }*/
            

    @Override
    public boolean delete(Clubs c)throws SQLException  {
        try {
            String requete = " delete from club where id='"+c.getId()+"'" ;
            pst = con.prepareStatement(requete);
              ste=con.createStatement();
            ste.executeUpdate(requete);
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClubs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true ;
    }

    
    public void update(Clubs t,User u,int id){
        try {
            String requete = " update club set nom=? , categorie=? , description=? , date_fondation=? , nbmembres=? , responsable=?   where id='"+id+"'"  ;
            pst = con.prepareStatement(requete);
            pst.setString(1,t.getNom());
            pst.setString(2,t.getCategorie());
            pst.setString(3,t.getDescription());
            pst.setDate(4, t.getDatefondation());
            pst.setInt(5,t.getNbmembres());
            pst.setString(6,u.getResponsable());
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClubs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Clubs> readAll() throws SQLException {
    List<Clubs> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from club");
     while (rs.next()) { 
               User U = new User(rs.getString("responsable"));
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String categorie=rs.getString(3);
               String description=rs.getString("description");
               java.sql.Date date_fondation=rs.getDate("date_fondation"); 
               int nbmembres=rs.getInt(6);
               
               Clubs p=new Clubs(id, nom, categorie, description,date_fondation,nbmembres,U);
     arr.add(p);
     }
    return arr;
    }
    
    @Override
    public List<Clubs> getTrier() throws SQLException {
    List<Clubs> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from club ORDER BY nom DESC");
     while (rs.next()) {                
               User U = new User(rs.getString("responsable"));
               int id=rs.getInt(1);
               String nom=rs.getString("nom");
               String categorie=rs.getString(3);
               String description=rs.getString("description");
               java.sql.Date date_fondation=rs.getDate("date_fondation"); 
               int nbmembres=rs.getInt(6);
               Clubs p=new Clubs(id, nom, categorie, description,date_fondation,nbmembres,U);
     arr.add(p);
     }
    return arr;
    }

  public Clubs getById(int id) {
          Clubs a = null;
         String requete = " select* from club  where id='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {User U = new User(res.getString(7));
                a=new Clubs(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getDate(5),res.getInt(6),U);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClubs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
  public Clubs getByName(String n) {
          Clubs a = null;
         String requete = " select* from club  where (nom like '"+n+"%')" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {User U = new User(res.getString(7));
                a=new Clubs(res.getInt(1),res.getString(2),res.getString(3),res.getString(4),res.getDate(5),res.getInt(6),U);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClubs.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
  public void getDefendants( String db) throws Exception  { 
        
        
        @SuppressWarnings("unused")
        Workbook readWorkbook = WorkbookFactory.create(new FileInputStream("D:\\test2.xls") );
        @SuppressWarnings("resource")
        Workbook writeWorkbook = new HSSFWorkbook();
        Sheet desSheet = writeWorkbook.createSheet("new sheet");

        Statement stmt = null;
        ResultSet rs = null;
        try{
            String query ="SELECT * FROM "+db;

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            Row desRow1 = desSheet.createRow(0);
            for(int col=0 ;col < columnsNumber;col++) {
                Cell newpath = desRow1.createCell(col);
                newpath.setCellValue(rsmd.getColumnLabel(col+1));
            }
            while(rs.next()) {
                System.out.println("Row number" + rs.getRow() );
                Row desRow = desSheet.createRow(rs.getRow());
                for(int col=0 ;col < columnsNumber;col++) {
                    Cell newpath = desRow.createCell(col);
                    newpath.setCellValue(rs.getString(col+1));  
                }
                FileOutputStream fileOut = new FileOutputStream("D:\\test2.xls");
                writeWorkbook.write(fileOut);
                fileOut.close();
            }
        }
        catch (SQLException e) {
            System.out.println("Failed to get data from database");
        }
    }
  
   
  
  public List<Clubs> displayClause(String cl) throws SQLException {
        String requeteInsert = "Select * from club "+cl+";";
        List<Clubs> clss=new ArrayList<>();
        ste = con.prepareStatement(requeteInsert);
        ResultSet rs=ste.executeQuery(requeteInsert);
        while(rs.next())
        {   User U = new User(rs.getString("responsable"));
            clss.add(new Clubs(rs.getInt(1),rs.getString("nom"),rs.getString("categorie"),rs.getString("description"),rs.getDate("date_fondation"),rs.getInt("nbmembres"),U));
            System.out.println(clss.isEmpty());
        }
        return clss;
    }
  
  public ObservableList<PieChart.Data> getBookGraphStatistics() {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
        try {
            String qu1 = "SELECT COUNT id FROM club where categorie='evenement' ";
            String qu2 = "SELECT COUNT id FROM club where categorie='social'";
            ResultSet rs = execQuery(qu1);
            if (rs.next()) {
                int count = rs.getInt(1);
                data.add(new PieChart.Data("evenement  (" + count + ")", count));
            }
            rs = execQuery(qu2);
            if (rs.next()) {
                int count = rs.getInt(1);
                data.add(new PieChart.Data("social (" + count + ")", count));
            }
        }
        catch (SQLException e) {
        }
        return data;
    }  
  
  public int calculer( String c) {
          int l = 0 ;
         String requete = "SELECT COUNT(id) FROM club WHERE categorie='"+c+"' " ;
        try {
           
           Statement st =con.createStatement();
           ResultSet rs=st.executeQuery(requete);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
           l=Integer.parseInt(chaine);
            System.out.println(l);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClubs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return l ;
    }
  
  public int calculerall() {
          int l = 0 ;
         String requete = "SELECT COUNT(*) FROM club " ;
        try {
           
           Statement st =con.createStatement();
           ResultSet rs=st.executeQuery(requete);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
           l=Integer.parseInt(chaine);
            System.out.println(l);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClubs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return l ;
    }

   

    @Override
    public boolean update(Clubs t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Clubs getById(Clubs f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter(Clubs t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private ResultSet execQuery(String qu2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
