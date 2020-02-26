/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Olympians.Service;


import com.Olympians.Entite.Abscence;
import com.Olympians.Entite.Matiere;
import com.Olympians.Entite.Punition;
import com.Olympians.Entite.Reclamation;
import com.Olympians.Entite.User;
import com.Olympians.IService.IService;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.sql.PreparedStatement;
import com.Olympians.Utils.DataBase;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 *
 * @author House
 */
public class ServiceReclamations implements IService<Reclamation> {

    private Connection con;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;

    public ServiceReclamations() {
        con = DataBase.getInstance().getConnection();

    }

  /*  public void ajouter(Reclamation t,User u) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO reclamation (`id`, `id_user`,`date_creation`, `type`, `contenu`, `etat`) VALUES (NULL, '" + u.getId() + "',NULL, '" + t.getType() + "', '" + t.getContenu() + "', '" + t.getEtat() + "');";
        try {
            ste=con.createStatement();
            ste.executeUpdate(requeteInsert);
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    public void ajouter(Reclamation r,User u,String type) throws SQLException
  { 
     
      ste = con.createStatement();
        try {
         
           
       
           switch (type) {
               case "note":
                   if(existMatiere(r.getIdMatiere().getIdM())!=0)
                   { 
                       String req="insert into reclamation (id_user,type,contenu,etat,nom_user,idmatiere) values(?,?,?,?,?,?)";
                       pst=con.prepareStatement(req);
                       pst.setString(3, r.getContenu()); //1ere pt d'interrogation
                       pst.setString(2, "note");
                       
                       pst.setString(4, "non");
                       pst.setInt(1, u.getId());
                       pst.setString(5, u.getNom());
                       pst.setInt(6, r.getIdMatiere().getIdM());
                       pst.executeUpdate();
                   }
                   else
                   {
                       System.out.println("l'id de la matière  que vous pouvez reclamer n'existe pas");
                   }      break;
               case "abscence":
                   if(existAbscence(r.getIdAbscence().getIdAbs())!=0)
                   {  String req="insert into reclamation (id_user,type,contenu,etat,nom_user,idabscence) values(?,?,?,?,?,?)";
                   pst=con.prepareStatement(req);
                   pst.setString(3, r.getContenu()); //1ere pt d'interrogation
                   pst.setString(2, "abscence");
                   
                    pst.setString(4, "non");
                   pst.setInt(1, u.getId());
                   pst.setString(5, u.getNom());
                   pst.setInt(6, r.getIdAbscence().getIdAbs());
                   pst.executeUpdate();
                   }
                   else {
                       System.out.println("l'id de l'abscence que vous pouvez reclamer n'existe pas");
                   } break;
               case "punition":
                   if(existpunition(r.getIdPunition().getIdPun())!=0)
                   {    String req="insert into reclamation (id_user,type,contenu,etat,nom_user,idpunition) values(?,?,?,?,?,?)";
                   pst=con.prepareStatement(req);
                   pst.setString(3, r.getContenu()); //1ere pt d'interrogation
                   pst.setString(2, "punition");
                   
                    pst.setString(4, "non");
                   pst.setInt(1, u.getId());
                   pst.setString(5, u.getNom());
                   pst.setInt(6, r.getIdPunition().getIdPun());
                   pst.executeUpdate();
                   }
                   else
                   {
                       System.out.println("l'evenement que vous pouvez reclamer n'existe pas");
                   }      break;
               default:
                   if(exictenceNomComplet(r.getNomUser().getNom())!=0)
                   {    String req="insert into reclamation (id_user,type,contenu,etat,nom_user) values(?,?,?,?,?,?)";
                   pst=con.prepareStatement(req);
                   pst.setString(1, r.getContenu()); //1ere pt d'interrogation
                   pst.setString(2, "autre");
                   
                   
                    pst.setString(4, "non");
                    pst.setInt(5, u.getId());
                   pst.setString(6,r.getNomUser().getNom());
                   pst.executeUpdate();
                   }
                   else System.out.println("le nom de l'utilisateur que vous reclamer n'existe pas");
                   break;
           }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamations.class.getName()).log(Level.SEVERE, null, ex);
        }
      
  }
    
     public int exictenceNomComplet(String nom)
     {
          int i=0; 
         
         String noma = "'"+nom+"'";
         String req="select  *  from utilisateur where nom ="+noma;
       try {
            ste=con.createStatement();
            res=ste.executeQuery(req);
       
     while(res.next())
   
    { 
               
       i=i+1;
        
    }
  
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamations.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return i;
         
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
    public boolean delete(Reclamation c)  {
        try {
            String requete = " delete from reclamation where id='"+c.getId()+"'" ;
            pst = con.prepareStatement(requete);
              ste=con.createStatement();
            ste.executeUpdate(requete);
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true ;
    }

    
    public void update(Reclamation t,int id){
        try {
            String requete = " update reclamation set contenu=?     where id='"+id+"'"  ;
            pst = con.prepareStatement(requete);
            
            pst.setString(1,t.getContenu());
            
           
            pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        public void supprimerReclamationparEtat() 
    {
       
           try {  
                String q ="delete from reclamation where etat='oui'";
                
             ste=con.createStatement();
        ste.executeUpdate(q);
               System.out.println("tu as bien supprimé");
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamations.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }   
     public void TraitementReclamation(int id,String etat)
 {
    String et = "'"+etat+"'";                                                                                                                              
        try {  

        String query="update reclamation set etat='"+etat+"' where id="+id ;
        ste=con.createStatement();
        ste.executeUpdate(query);   
      System.out.println("bien modifiée");
             
            
          
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamations.class.getName()).log(Level.SEVERE, null, ex);
        } 
 }    

    /**
     *
     * @return
     * @throws SQLException
     */
    @Override
    public List<Reclamation> readAll() throws SQLException {
    List<Reclamation> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation");
     while (rs.next()) { 
         DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
      Timestamp timestamp = new Timestamp(System.currentTimeMillis());
               User U = new User(rs.getInt(2));
               User U2=new User(rs.getInt(2),rs.getString("nom_user"));
               int id=rs.getInt(1);
             
               String type=rs.getString("type");
               String contenu=rs.getString("contenu");
               String etat=rs.getString("etat");
                Matiere m=new Matiere(rs.getInt("idmatiere"));
                Abscence a =new Abscence(rs.getInt("idabscence"));
                Punition p2 =new Punition(rs.getInt("idpunition"));
               Reclamation p=new Reclamation(id,U,U2, rs.getTimestamp("date_creation"), type,a,p2,m, contenu,etat);
     arr.add(p);
     }
    return arr;
    }
    
        public List<Reclamation> displayAll(User id_user) {
        
       List<Reclamation> list=new ArrayList();
        System.out.println(id_user);
        int ir=id_user.getId();
       String req="select * from reclamation where id_user="+ir;
        try {
            ste=con.createStatement();
              res=ste.executeQuery(req);
       
               System.out.println("les reclamation que vous faites sont:");
            
     while(res.next())
     {        int id=res.getInt(1);
              User U = new User(res.getString("id_user"));
               java.sql.Timestamp date_creation=res.getTimestamp("date_creation");
               String type=res.getString("type");
               String contenu=res.getString("contenu");
               String etat=res.getString("etat");
              Matiere m=new Matiere(res.getInt(8));
              Abscence a=new Abscence(res.getInt(9));
              Punition p=new Punition(res.getInt(10));
              
        
     list.add(new Reclamation(id,U, date_creation, type,a,p,m, contenu,etat)); 
     
 
      
         
     }
   
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamations.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        return list;
      
    }
    
    @Override
    public List<Reclamation> getTrier() throws SQLException {
    List<Reclamation> arr=new ArrayList<>();
    ste=con.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation ORDER BY nom DESC");
     while (rs.next()) {                
               User U = new User(rs.getString("id_user"));
               int id=rs.getInt(1);
               java.sql.Timestamp date_creation=rs.getTimestamp("date_creation");
               String type=rs.getString("type");
               String contenu=rs.getString("contenu");
               String etat=rs.getString("etat");
              
               
               
               Reclamation p=new Reclamation(id,U, date_creation, type, contenu,etat);
     arr.add(p);
     }
    return arr;
    }

  public Reclamation getByIdR(int id) {
          Reclamation a = null;
         String requete = " select* from reclamation  where id='"+id+"'" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {User U = new User(res.getString(7));
                a=new Reclamation(res.getInt(1),U,res.getTimestamp(3),res.getString(4),res.getString(5),res.getString(6));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
  public Reclamation getByName(String n) {
          Reclamation a = null;
         String requete = " select* from reclamation  where (type like '"+n+"%')" ;
        try {
           
            ste = con.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {User U = new User(res.getString(7));
               a=new Reclamation(res.getInt(1),U,res.getTimestamp(3),res.getString(4),res.getString(5),res.getString(6));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
public int existMatiere(int id)
     {
          int i=0; 
         
         
         String req="select  *  from matiere where id ="+id;
       try {
            ste=con.createStatement();
            res=ste.executeQuery(req);
       
     while(res.next())
 
    { 
 i=i+1;
        
    }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamations.class.getName()).log(Level.SEVERE, null, ex);
        }
 
         return i;
   
     }
public int existAbscence(int id)
     {
          int i=0; 
         
         
         String req="select  *  from absence where id ="+id;
       try {
            ste=con.createStatement();
            res=ste.executeQuery(req);
       
     while(res.next())
 
    { 
 i=i+1;
        
    }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamations.class.getName()).log(Level.SEVERE, null, ex);
        }
 
         return i;
   
     }

public int existpunition(int id)
     {
          int i=0; 
         
         
         String req="select  *  from sanction where idpunition ="+id;
       try {
            ste=con.createStatement();
            res=ste.executeQuery(req);
       
     while(res.next())
 
    { 
 i=i+1;
        
    }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamations.class.getName()).log(Level.SEVERE, null, ex);
        }
 
         return i;
   
     }

public void getDefendants( String db) throws Exception  { 
        
        
        @SuppressWarnings("unused")
        Workbook readWorkbook = WorkbookFactory.create(new FileInputStream("D:\\test3.xls") );
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
                FileOutputStream fileOut = new FileOutputStream("D:\\test3.xls");
                writeWorkbook.write(fileOut);
                fileOut.close();
            }
        }
        catch (SQLException e) {
            System.out.println("Failed to get data from database");
        }
    }

public List<Reclamation> displayClause(String cl) throws SQLException {
        String requeteInsert = "Select * from reclamation "+cl+";";
        List<Reclamation> clss=new ArrayList<>();
        ste = con.prepareStatement(requeteInsert);
        ResultSet rs=ste.executeQuery(requeteInsert);
        while(rs.next())
        {      
              User U = new User(rs.getString("id_user"));
               java.sql.Timestamp date_creation=rs.getTimestamp("date_creation");
               String type=rs.getString("type");
               String contenu=rs.getString("contenu");
               String etat=rs.getString("etat");
              Matiere m=new Matiere(rs.getInt(8));
              Abscence a=new Abscence(rs.getInt(9));
              Punition p=new Punition(rs.getInt(10));
              clss.add(new Reclamation(rs.getInt(1),U, date_creation, type,a,p,m, contenu,etat)); 
            System.out.println(clss.isEmpty());
        }
        return clss;
    }
 public int calculer( String c) {
          int l = 0 ;
         String requete = "SELECT COUNT(id) FROM reclamation WHERE type='"+c+"' " ;
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
 
 public int calculertraiter( String c) {
          int l = 0 ;
         String requete = "SELECT COUNT(id) FROM reclamation WHERE etat='"+c+"' " ;
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
         String requete = "SELECT COUNT(*) FROM reclamation " ;
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
    public void ajouter(Reclamation t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public boolean update(Reclamation t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   

    @Override
    public Reclamation getById(Reclamation f) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
