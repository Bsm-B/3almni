/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gn.module.reclamation;

import com.Olympians.Entite.Abscence;

import com.Olympians.Entite.Matiere;
import com.Olympians.Entite.Punition;
import com.Olympians.Entite.Reclamation;
import com.Olympians.Entite.User;
import com.Olympians.Service.SendMail;
import com.Olympians.Service.ServiceClubs;
import com.Olympians.Service.ServiceReclamations;
import com.gn.global.util.Alerts;
import com.gn.module.club.ClubsController;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import doryan.windowsnotificationapi.fr.Notification;
import java.awt.AWTException;
import java.awt.TrayIcon;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author saidi
 */
public class ReclamationController implements Initializable {

       
       @FXML private TableView<Reclamation> tableView;
    @FXML private TableColumn<String, Reclamation> c1;
    @FXML private TableColumn<String, Reclamation> c2;
    @FXML private TableColumn<String, Reclamation> c3;
    @FXML private TableColumn<String, Reclamation> c4;
    @FXML private TableColumn<String, Reclamation> c5;
    @FXML private TableColumn<String, Reclamation> c6;
    @FXML private TableColumn<String, Reclamation> c7;
    @FXML private TableColumn<String, Reclamation> c8;
    @FXML private TableColumn<String, Reclamation> c9;
    @FXML private TableColumn<String, Reclamation> c10;
    
    
    @FXML private TextField id_user;
  
    @FXML private TextField contenu;
    @FXML private TextField etat;
    @FXML private TextField nomuser;
    @FXML private TextField idabscence;
    @FXML private TextField idpunition;
    @FXML private TextField idmatiere;
    @FXML private TextField nomS;
    @FXML private Label label ;
    @FXML private Label traite ;
    @FXML private Label nontraite ;
  
    @FXML private BarChart<String, Number> barChart;
      @FXML
    private ComboBox<String> type;
    ServiceClubs ser = new ServiceClubs();
    ObservableList<String> listType = FXCollections.observableArrayList("note","punition","abscence","autre");
 
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
        ServiceReclamations ser = new ServiceReclamations();
         ObservableList<String> listType = FXCollections.observableArrayList("note","punition","abscence","autre");
         type.setItems(listType);
        List<Reclamation> list = ser.readAll();
        ObservableList<Reclamation> cls = FXCollections.observableArrayList();
        for (Reclamation aux : list)
        {  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
      Timestamp date_creation = new Timestamp(System.currentTimeMillis());
      String date= (String) aux.getDateCreation().toString();
          cls.add(new Reclamation(aux.getId(),aux.getIdUser(),aux.getNomUser(),aux.getDateCreation(), aux.getType(),aux.getIdAbscence(),aux.getIdPunition(),aux.getIdMatiere(), aux.getContenu(),aux.getEtat()));  
        }
        
        c1.setCellValueFactory(new PropertyValueFactory<>("id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("IdUsers"));
        c3.setCellValueFactory(new PropertyValueFactory<>("NomUsers"));
        c4.setCellValueFactory(new PropertyValueFactory<>("DateCreation"));
        c5.setCellValueFactory(new PropertyValueFactory<>("type"));
        c6.setCellValueFactory(new PropertyValueFactory<>("IdAbscences"));
        c7.setCellValueFactory(new PropertyValueFactory<>("IdPunitions"));
        c8.setCellValueFactory(new PropertyValueFactory<>("IdMatieres"));
        c9.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        c10.setCellValueFactory(new PropertyValueFactory<>("etat"));
        
        
        
        
        tableView.setItems(cls);
        }
        catch (SQLException ex) {
                    System.out.println(ex);
                 }
        ServiceReclamations ser = new ServiceReclamations();
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Répartition des réclamations");
        series.getData().add(new XYChart.Data<>("note", ser.calculer("note")));
        series.getData().add(new XYChart.Data<>("punition", ser.calculer("punition")));
        series.getData().add(new XYChart.Data<>("absence", ser.calculer("abscence")));
        series.getData().add(new XYChart.Data<>("autre", 0));
       barChart.getData().addAll(series);
        
         
        int a=ser.calculerall();
        int a1=ser.calculertraiter("non");
        int a2=ser.calculertraiter("oui");
        
        label.setText(String.valueOf(a));
        
        traite.setText(String.valueOf(a1));
        nontraite.setText(String.valueOf(a2));
        
    }
        
    @FXML
    public void Add(){
        
        try {
                  int Iduser= (int) Integer.valueOf(id_user.getText());
                  ComboBox types = (ComboBox) type ;
                 String Type =(String) types.getValue().toString();
               
                
                 String Contenu1= (String) contenu.getText();
                 
                 
                 
                 
                 
                 String Nomuser= (String) nomuser.getText();
                
           
                ServiceReclamations ser = new ServiceReclamations();
               if("punition".equals((String) types.getValue().toString())){
                Punition p =new Punition((int) Integer.valueOf(idpunition.getText()));
                   System.out.println("ok1");
                 Reclamation C1 = new Reclamation((String) contenu.getText(), p);
                 System.out.println("ok2");
                 User u = new User((int) Integer.valueOf(id_user.getText()),(String) nomuser.getText());
                 System.out.println("ok3");
                    ser.ajouter(C1,u,Type);
                    System.out.println("ok5");
               
               }else if ("abscence".equals((String) types.getValue().toString())){
                Abscence a =new Abscence((int) Integer.valueOf(idabscence.getText()));
                 Reclamation C2 = new Reclamation((String) contenu.getText(), a);
                 User u = new User((int) Integer.valueOf(id_user.getText()),(String) nomuser.getText());
                 
                    ser.ajouter(C2,u,Type);
               
               }else if ("note".equals((String) types.getValue().toString())){
                Matiere m =new Matiere((int) Integer.valueOf(idmatiere.getText()));
                 Reclamation C3 = new Reclamation((String) contenu.getText(), m);
                 User u = new User((int) Integer.valueOf(id_user.getText()),(String) nomuser.getText());
                 
                    ser.ajouter(C3,u,Type);
               
               }
              
                   
               
                     List<Reclamation> list = ser.readAll();
                     ObservableList<Reclamation> cls = FXCollections.observableArrayList();
                     for (Reclamation aux : list)
                     {
                      cls.add(new Reclamation(aux.getId(),aux.getIdUser(),aux.getNomUser(),aux.getDateCreation(), aux.getType(),aux.getIdAbscence(),aux.getIdPunition(),aux.getIdMatiere(), aux.getContenu(),aux.getEtat()));  
                     }
                     tableView.setItems(cls);
                     
                    Alerts.success("Success", "Votre reclamation a bien été ajouté."); 
                      
        int a=ser.calculerall();
        int a1=ser.calculertraiter("non");
        int a2=ser.calculertraiter("oui");
        
        label.setText(String.valueOf(a));
        
        traite.setText(String.valueOf(a1));
        nontraite.setText(String.valueOf(a2));
     
                    
                 }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
        
    }
    
    @FXML
    public void Update(){
        Reclamation tmp=new Reclamation(); 
        if(!tmp.equals(tableView.getSelectionModel().getSelectedItem())){
        try {
                 
                   
                
                
                
                 
                ServiceReclamations ser = new ServiceReclamations();
                Reclamation tmp2=tableView.getSelectionModel().getSelectedItem();
                
                 
                 
                 ser.TraitementReclamation(tmp2.getId(),(String) etat.getText());
                    
                     List<Reclamation> list = ser.readAll();
                     ObservableList<Reclamation> cls = FXCollections.observableArrayList();
                      try {
         Notification.sendNotification("réclamation traité", "votre reclamation a bien été traité ",TrayIcon.MessageType.INFO);
     } catch (AWTException ex) {
         Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (MalformedURLException ex) {
         Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
     }
                     for (Reclamation aux : list)
                     {
                       cls.add(new Reclamation(aux.getId(),aux.getIdUser(),aux.getNomUser(),aux.getDateCreation(), aux.getType(),aux.getIdAbscence(),aux.getIdPunition(),aux.getIdMatiere(), aux.getContenu(),aux.getEtat())); 
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
        Reclamation tmp=new Reclamation(); 
        if(!tmp.equals(tableView.getSelectionModel().getSelectedItem())){
        try {
                 
                 
                 ServiceReclamations ser = new ServiceReclamations();
                 Reclamation tmp2=tableView.getSelectionModel().getSelectedItem();
                 
                 Reclamation C1 = new Reclamation(tmp2.getId(),tmp2.getIdUser(), tmp2.getNomUser(), tmp2.getDateCreation(),tmp2.getType(),tmp2.getIdAbscence(),tmp2.getIdPunition(),tmp2.getIdMatiere(),tmp2.getContenu(),tmp2.getEtat());
                 
                 
                    ser.delete(C1);
                     List<Reclamation> list = ser.readAll();
                     ObservableList<Reclamation> cls = FXCollections.observableArrayList();
                     for (Reclamation aux : list)
                     {
                      cls.add(new Reclamation(aux.getId(),aux.getIdUser(),aux.getNomUser(),aux.getDateCreation(), aux.getType(),aux.getIdAbscence(),aux.getIdPunition(),aux.getIdMatiere(), aux.getContenu(),aux.getEtat())); 
                     }
                     tableView.setItems(cls);
                   Alerts.error("Supprimer", "Votre reclamation a bien été supprimé."); 
                    int a=ser.calculerall();
        int a1=ser.calculertraiter("non");
        int a2=ser.calculertraiter("oui");
        
        label.setText(String.valueOf(a));
        
        traite.setText(String.valueOf(a1));
        nontraite.setText(String.valueOf(a2));
                 }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
        
        }
        
    }
    @FXML
    public void Search(){
       
        try {
                 
                 
                ServiceReclamations se = new ServiceReclamations();
                     List<Reclamation> list = se.displayClause(" WHERE type LIKE '%"+nomS.getText()+"%' or contenu LIKE '%"+nomS.getText()+"%' or etat LIKE '%"+nomS.getText()+"%' or id LIKE '%"+nomS.getText()+"%'");
                     ObservableList<Reclamation> cls = FXCollections.observableArrayList();
                     for (Reclamation aux : list)
                     {
             cls.add(new Reclamation(aux.getId(),aux.getIdUser(),aux.getNomUser(),aux.getDateCreation(), aux.getType(),aux.getIdAbscence(),aux.getIdPunition(),aux.getIdMatiere(), aux.getContenu(),aux.getEtat()));                     }
                     tableView.setItems(cls);
                    
                 }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
        }
    @FXML
     public void Excel() throws Exception{
       
        try {
             ServiceReclamations ser = new ServiceReclamations();
            ser.getDefendants("reclamation");
                 
              
        }catch (SQLException e) {
                   System.out.println("Failed to get data from database");
                 }
       
    
} 
      @FXML
     public void handle() throws SQLException {
          
                String ad="D:\\List_reclamations.pdf";
                Document doc=new Document();
                Alert dialogC = new Alert(Alert.AlertType.INFORMATION);
             dialogC.setTitle(" Confirmation ");
             dialogC.setHeaderText(null);
             dialogC.setContentText("Are you sure to export pdf ");
                          Optional<ButtonType> answer = dialogC.showAndWait();
               try {
                   PdfWriter.getInstance(doc, new FileOutputStream(ad));
               } catch (FileNotFoundException ex) {
                   Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
               } catch (DocumentException ex) {
                   Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
               }
               doc.open();
               try {
                    doc.add(new Paragraph("Reclamation"));
                    doc.add(new Paragraph("Reclamation List"));
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                    LocalDateTime now = LocalDateTime.now();  
                    String d=dtf.format(now);
                    doc.add(new Paragraph("Date: "+d));
                    doc.add(new Paragraph(" "));
                    PdfPTable table = new PdfPTable(5);
                    PdfPCell c1=new PdfPCell(new Phrase("id"));
                    table.addCell(c1);
                    c1=new PdfPCell(new Phrase("Username"));
                    table.addCell(c1);
                    c1=new PdfPCell(new Phrase("Contenu"));
                    table.addCell(c1);
                    c1=new PdfPCell(new Phrase("DateCreation"));
                    table.addCell(c1);
                    c1=new PdfPCell(new Phrase("Etat"));
                    table.addCell(c1);
                   
                    // table.setHeaderRows(0);
                    ServiceReclamations s=new ServiceReclamations();
                    ArrayList<Reclamation> e =(ArrayList<Reclamation>)s.readAll();
                    for(int i=0;i<e.size();i++)
                    {
                         int id=e.get(i).getId();
                        table.addCell(String.valueOf(id));  
                        
                        String nom=e.get(i).getNomUsers();
                        table.addCell(String.valueOf(nom));
                        
                        String u=e.get(i).getContenu();
                        table.addCell(u);
                        
                        Timestamp time=e.get(i).getDateCreation();
                        table.addCell(String.valueOf(time));
                        
                        String et=e.get(i).getEtat();
                        table.addCell(et);
                        
                        
                             
                       
                        
                    }
                    doc.add(table);
               }catch (DocumentException ex) {
                   Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
               }
               doc.close();
                }
   
     
    
                 

    
        public void Notification() {
       
        try {
         Notification.sendNotification("réclamation traité", "votre reclamation a bien été traité ",TrayIcon.MessageType.INFO);
     } catch (AWTException ex) {
         Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
     } catch (MalformedURLException ex) {
         Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
     }
       
    
}
    
    
}
