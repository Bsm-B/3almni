package com.gn.module.matiers;

import com.Olympians.Entite.Classroom;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import com.Olympians.Entite.Matiers;
import com.Olympians.Service.ServiceMatiers;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert;



public class matiers implements Initializable{

    @FXML
    private VBox gray1;

    @FXML
    private VBox gray11;
        
    @FXML private TableView<Matiers> tableView;
    @FXML private TableColumn<String, Matiers> c1;
    @FXML private TableColumn<String, Matiers> c2;
    @FXML private TableColumn<String, Matiers> c3;
    @FXML private TableColumn<String, Matiers> c4;
    
    @FXML private TextField id;
    @FXML private TextField nom;
    @FXML private TextField coff;
    @FXML private TextField cheff;
    @FXML private TextField search;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
     ServiceMatiers ServMat = new ServiceMatiers();
        List<Matiers> list = ServMat.readAll();
        System.out.println(list);
        ObservableList<Matiers> cls = FXCollections.observableArrayList();
        for (Matiers aux : list)
        {
          cls.add(new Matiers(aux.getId(),aux.getNom(), aux.getCoefficient(), aux.getChef_module()));  
        }
       c1.setCellValueFactory(new PropertyValueFactory<>("id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("nom"));
     c3.setCellValueFactory(new PropertyValueFactory<>("coefficient"));
        c4.setCellValueFactory(new PropertyValueFactory<>("chef_module"));
        
       tableView.setItems(cls);
        }
        catch (SQLException ex) {
                    System.out.println(ex);
                 }
    }
    
    
            
    @FXML
    public void Add(){
        
        try {
                 
                 String Name= (String) nom.getText();
                 double Coff = Double.valueOf(coff.getText());
                 String Cheff= (String) cheff.getText();
                 
               
                  ServiceMatiers ServMat = new ServiceMatiers();
                   Matiers M1 = new Matiers(Name,Coff,Cheff);
                   ServMat.ajouter(M1);
                   Afficher();
              
                 }
                 catch (SQLException ex) {
  
                    System.out.println(ex);
                 }
        
    }
    
    
    
        @FXML
    public void Delete(){
        ServiceMatiers tmp=new ServiceMatiers(); 
       if(!tmp.equals(tableView.getSelectionModel().getSelectedItem())){
        try {   
                 ServiceMatiers ser = new ServiceMatiers();
                 Matiers tmp2=tableView.getSelectionModel().getSelectedItem();
                   Matiers M1 =  new Matiers(tmp2.getId(),tmp2.getNom(), tmp2.getCoefficient(), tmp2.getChef_module());
                   ser.delete(M1.getId());
                    Afficher();
                    nom.setText("");
                   coff.setText("");
                 cheff.setText("");
                 }
                 catch (SQLException ex) {
               
                    System.out.println(ex);
                 }
        }
        
       
    

}
    
    
 
    @FXML
    public void Update(){
        Matiers tmp=new Matiers(); 
        if(!tmp.equals(tableView.getSelectionModel().getSelectedItem())){
        try {
                 
                                 
                 String Name= (String) nom.getText();
                 double Coff = Double.valueOf(coff.getText());
                 String Cheff= (String) cheff.getText();
              
                  ServiceMatiers ServMat = new ServiceMatiers();
                  
                 Matiers tmp2=tableView.getSelectionModel().getSelectedItem();
                Matiers M1 =  new Matiers(tmp2.getId(),Name, Coff, Cheff);
                    ServMat.update(M1,M1.getId());
                    Afficher();
                   
                 }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
        }
        
        
    } 
    
    
    @FXML
   public void GetData()
    {
        try{
            Matiers M1=tableView.getSelectionModel().getSelectedItem();
            nom.setText(M1.getNom());
            coff.setText(String.valueOf(M1.getCoefficient()));
            cheff.setText(M1.getChef_module());
            
        }catch(Exception ex){
            
               System.out.println(ex);
        }
                        

    }
   
   
   
    @FXML
   public void Serach()
    {
        try{
          
                  ServiceMatiers ServMat = new ServiceMatiers();
                
                List<Matiers> list = ServMat.Search(search.getText());
                ObservableList<Matiers> cls = FXCollections.observableArrayList();


                     for (Matiers aux : list)
                {
                    cls.add(new Matiers(aux.getId(),aux.getNom(), aux.getCoefficient(), aux.getChef_module()));  
                }
                     tableView.setItems(cls);
                    
                  
                  
        }catch(Exception ex){
            
               System.out.println(ex);
        }
                        

    }
  
  
   
  
    
  void Afficher(){
          try {
                 
              
                 
               
               
                  ServiceMatiers ServMat = new ServiceMatiers();
                 ObservableList<Matiers> cls = FXCollections.observableArrayList();
                List<Matiers> list = ServMat.readAll();

                     for (Matiers aux : list)
                {
                    cls.add(new Matiers(aux.getId(),aux.getNom(), aux.getCoefficient(), aux.getChef_module()));  
                }
                     tableView.setItems(cls);
                    
                 }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
        
  }
  
  

}