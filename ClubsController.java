/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gn.module.club;



import com.Olympians.Entite.Clubs;
import com.Olympians.Entite.User;

import com.Olympians.Service.SendMail;
import com.Olympians.Service.ServiceClubs;
import static com.Olympians.Service.Whatsapp.ACCOUNT_SID;
import static com.Olympians.Service.Whatsapp.AUTH_TOKEN;
import com.gn.global.util.Alerts;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;




import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import javafx.scene.control.DatePicker;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;



/**
 * FXML Controller class
 *
 * @author saidi
 */
public class ClubsController implements Initializable {

    @FXML private TableView<Clubs> tableView;
    @FXML private TableColumn<String, Clubs> c1;
    @FXML private TableColumn<String, Clubs> c2;
    @FXML private TableColumn<String, Clubs> c3;
    @FXML private TableColumn<String, Clubs> c4;
    @FXML private TableColumn<String, Clubs> c5;
    @FXML private TableColumn<String, Clubs> c6;
    @FXML private TableColumn<String, Clubs> c7;
    
    
    @FXML private TextField nom;
    //@FXML private TextField categorie;
    @FXML private TextField description;
    private DatePicker date_fondatin;
    @FXML private TextField nbmembres;
    @FXML private TextField responsable;
    @FXML private TextField nomS;
    @FXML private TextField email;
    @FXML private TextField objet;
    @FXML private TextField corp;
    @FXML
    private DatePicker date_fondation;
    @FXML private PieChart bookChart ;
    @FXML private Label label ;
    
            @FXML
    private ComboBox<String> categorie;
    ServiceClubs ser = new ServiceClubs();
    ObservableList<String> listCategorie = FXCollections.observableArrayList("evenement","social","entrepreneriat","media","robotique");
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
        ServiceClubs ser = new ServiceClubs();
         ObservableList<String> listCategorie = FXCollections.observableArrayList("evenement","social","entrepreneriat","media","robotique");
         categorie.setItems(listCategorie);
        List<Clubs> list = ser.readAll();
        
        ObservableList<Clubs> cls = FXCollections.observableArrayList();
        for (Clubs aux : list)
        {
          cls.add(new Clubs(aux.getId(),aux.getNom(), aux.getCategorie(), aux.getDescription(),aux.getDatefondation(),aux.getNbmembres(),aux.getRes()));  
        }
        
        c1.setCellValueFactory(new PropertyValueFactory<>("id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("nom"));
        c3.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        c4.setCellValueFactory(new PropertyValueFactory<>("description"));
        c5.setCellValueFactory(new PropertyValueFactory<>("Datefondation"));
        c6.setCellValueFactory(new PropertyValueFactory<>("nbmembres"));
        c7.setCellValueFactory(new PropertyValueFactory<>("responsables"));
        
        
        
        tableView.setItems(cls);
        }
        catch (SQLException ex) {
                    System.out.println(ex);
                 }
        ServiceClubs ser = new ServiceClubs();
        
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                
                new PieChart.Data("Social", ser.calculer("social")),
                new PieChart.Data("Evenement", ser.calculer("evenement")),
                new PieChart.Data("Entrepreneriat",ser.calculer("entrepreneriat")),
                new PieChart.Data("Robotique", ser.calculer("robotique")),
                new PieChart.Data("Media", ser.calculer("media"))
        );
        bookChart.setData(pieChartData);
        bookChart.setClockwise(false);
        
        int a=ser.calculerall();
       label.setText(String.valueOf(a));
      
    }
        
    @FXML
    public void Add(){
        
        try {
                 
                 String Name= (String) nom.getText();
                
                 ComboBox categories = (ComboBox) categorie ;
                 String Categorie =(String) categories.getValue().toString();
                 String Descrition= (String) description.getText();
                 
                 DatePicker tmpdate=(DatePicker) date_fondation;
                String date= (String) date_fondation.getValue().toString();
                date = date.substring(0,4)+'/'+date.substring(5,7)+'/'+date.substring(8);                
                java.util.Date myDate = new java.util.Date(date);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                 
                 String Responsable= (String) responsable.getText();
                 
              
                 
                 int Nbmembres= (int) Integer.valueOf(nbmembres.getText());
                 ServiceClubs ser = new ServiceClubs();
                 Clubs C1 = new Clubs(Name, Categorie, Descrition ,sqlDate,Nbmembres);
                 User u = new User(Responsable);
                 
                    ser.ajouter(C1,u);
                     List<Clubs> list = ser.readAll();
                     ObservableList<Clubs> cls = FXCollections.observableArrayList();
                     for (Clubs aux : list)
                     {
                      cls.add(new Clubs(aux.getId(),aux.getNom(), aux.getCategorie(), aux.getDescription(),aux.getDatefondation(),aux.getNbmembres(),aux.getRes()));  
                     }
                     tableView.setItems(cls);
                     
                       Alerts.success("Success", "Ce club a bien été ajouté.");
                     Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+21627404402"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                "Ce club:  "+nom.getText()+"a été ajouté à notre grande famille de club.")
           .create();
         int a=ser.calculerall();
       label.setText(String.valueOf(a));
                    
                 }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
        
    }
    
    @FXML
    public void Update(){
        Clubs tmp=new Clubs(); 
        if(!tmp.equals(tableView.getSelectionModel().getSelectedItem())){
        try {
                 
                   String Name= (String) nom.getText();
                
                 ComboBox categories = (ComboBox) categorie ;
                 String Categorie =(String) categories.getValue().toString();
                 
                 String Descrition= (String) description.getText();
                 
                 DatePicker tmpdate=(DatePicker) date_fondation;
                String date= (String) tmpdate.getValue().toString();
                date = date.substring(0,4)+'/'+date.substring(5,7)+'/'+date.substring(8);                
                java.util.Date myDate = new java.util.Date(date);
                java.sql.Date sqlDate = new java.sql.Date(myDate.getTime());
                 
                 String Responsable= (String) responsable.getText();
                 
                 int Nbmembres= (int) Integer.valueOf(nbmembres.getText());
                 ServiceClubs ser = new ServiceClubs();
                 Clubs tmp2=tableView.getSelectionModel().getSelectedItem();
                 Clubs C1 = new Clubs(Name, Categorie, Descrition ,sqlDate,Nbmembres);
                 User u = new User(Responsable);
                 
                    ser.update(C1,u,tmp2.getId());
                     List<Clubs> list = ser.readAll();
                     ObservableList<Clubs> cls = FXCollections.observableArrayList();
                     for (Clubs aux : list)
                     {
                      cls.add(new Clubs(aux.getId(),aux.getNom(), aux.getCategorie(), aux.getDescription(),aux.getDatefondation(),aux.getNbmembres(),aux.getRes()));  
                     }
                     tableView.setItems(cls);
                    
                 }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
        }
        
        
    }
    
    @FXML
    public void Delete(){
        Clubs tmp=new Clubs(); 
        if(!tmp.equals(tableView.getSelectionModel().getSelectedItem())){
        try {
                 
                 
                 ServiceClubs ser = new ServiceClubs();
                 Clubs tmp2=tableView.getSelectionModel().getSelectedItem();
                 
                 Clubs C1 = new Clubs(tmp2.getId(),tmp2.getNom(), tmp2.getCategorie(), tmp2.getDescription(),tmp2.getDatefondation(),tmp2.getNbmembres(),tmp2.getRes());
                 
                 
                    ser.delete(C1);
                     List<Clubs> list = ser.readAll();
                     ObservableList<Clubs> cls = FXCollections.observableArrayList();
                     for (Clubs aux : list)
                     {
                      cls.add(new Clubs(aux.getId(),aux.getNom(), aux.getCategorie(), aux.getDescription(),aux.getDatefondation(),aux.getNbmembres(),aux.getRes())); 
                     }
                     tableView.setItems(cls);
                  Alerts.error("Supprimer", "Ce club a bien été supprimé."); 
                   int a=ser.calculerall();
       label.setText(String.valueOf(a));
                 }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
        }
        
    }
    @FXML
    public void Search(){
       
        try {
                 
                 
                ServiceClubs ser = new ServiceClubs();
                     List<Clubs> list = ser.displayClause(" WHERE nom LIKE '%"+nomS.getText()+"%' or categorie LIKE '%"+nomS.getText()+"%' or description LIKE '%"+nomS.getText()+"%' or id LIKE '%"+nomS.getText()+"%'");
                     ObservableList<Clubs> cls = FXCollections.observableArrayList();
                     for (Clubs aux : list)
                     {
                      cls.add(new Clubs(aux.getId(),aux.getNom(), aux.getCategorie(), aux.getDescription(),aux.getDatefondation(),aux.getNbmembres(),aux.getRes()));
                     }
                     tableView.setItems(cls);
                    
                 }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
        }
    @FXML
     public void Excel() throws Exception{
       
        try {
             ServiceClubs ser = new ServiceClubs();
            ser.getDefendants("club");
                 
              
        }catch (SQLException e) {
                   System.out.println("Failed to get data from database");
                 }
       
    
}
     
    
                 
    @FXML
    public void sendmail() {
       
        String mail= (String) email.getText();
        String Objet= (String) objet.getText();
        String Corp= (String) corp.getText();
        SendMail.sendMail(mail,Objet, Corp);
       
    
}
    
       @FXML
     public void handle() throws SQLException {
          
                String ad="D:\\List_clubss.pdf";
                Document doc=new Document();
                Alert dialogC = new Alert(Alert.AlertType.INFORMATION);
             dialogC.setTitle(" Confirmation ");
             dialogC.setHeaderText(null);
             dialogC.setContentText("Are you sure to export pdf ");
                          Optional<ButtonType> answer = dialogC.showAndWait();
               try {
                   PdfWriter.getInstance(doc, new FileOutputStream(ad));
               } catch (FileNotFoundException ex) {
                   Logger.getLogger(ClubsController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (DocumentException ex) {
                   Logger.getLogger(ClubsController.class.getName()).log(Level.SEVERE, null, ex);
               }
               doc.open();
               try {
                    doc.add(new Paragraph("Club"));
                    doc.add(new Paragraph("Clubs List"));
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                    LocalDateTime now = LocalDateTime.now();  
                    String d=dtf.format(now);
                    doc.add(new Paragraph("Date: "+d));
                    doc.add(new Paragraph(" "));
                    PdfPTable table = new PdfPTable(6);
                    PdfPCell c1=new PdfPCell(new Phrase("id"));
                    table.addCell(c1);
                    c1=new PdfPCell(new Phrase("Nom"));
                    table.addCell(c1);
                    c1=new PdfPCell(new Phrase("Categorie"));
                    table.addCell(c1);
                    c1=new PdfPCell(new Phrase("Description"));
                    table.addCell(c1);
                    c1=new PdfPCell(new Phrase("Date dondation"));
                    table.addCell(c1);
                    c1=new PdfPCell(new Phrase("nbmembres"));
                    table.addCell(c1);
                   
                    // table.setHeaderRows(0);
                    ServiceClubs s = new ServiceClubs();
                    ArrayList<Clubs> e =(ArrayList<Clubs>)s.readAll();
                    for(int i=0;i<e.size();i++)
                    {
                         int id=e.get(i).getId();
                        table.addCell(String.valueOf(id));  
                        
                        String nom=e.get(i).getNom();
                        table.addCell(nom);
                        
                        
                         String et=e.get(i).getCategorie();
                        table.addCell(et);
                        
                         String des=e.get(i).getDescription();
                        table.addCell(des);
                        
                        Date date=e.get(i).getDatefondation();
                        table.addCell(String.valueOf(date));
                        
                        int nbm=e.get(i).getNbmembres();
                        table.addCell(String.valueOf(nbm));
                        
                        
                        
                       
                        
                        
                             
                       
                        
                    }
                    doc.add(table);
               }catch (DocumentException ex) {
                   Logger.getLogger(ClubsController.class.getName()).log(Level.SEVERE, null, ex);
               }
               doc.close();
                }
    
      
    
    @FXML
        public void Notification() {
       
        
        try {
            Notification.sendNotification("Nouveau club ", "welcome our new club ",MessageType.WARNING);
        } catch (AWTException ex) {
            Logger.getLogger(ClubsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ClubsController.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    
}

    @FXML
    private void pdf(ActionEvent event) {
    }
        
   

   
        
        
    
    
}
