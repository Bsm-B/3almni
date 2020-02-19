/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3lemni.GUI;

import pkg3lemni.utils.DataBase;
import pkg3lemni.service.SanctionService;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import pkg3lemni.entity.Sanction;

/**
 * FXML Controller class
 *
 * @author wajih
 */
/*public class ScreenSanctionController implements Initializable {

    @FXML
    private TextField tfEmploye;
    @FXML
    private TextField tfEleve;
    @FXML
    private ComboBox<String> cbNature;
    @FXML
    private TextArea taCommentaire;
    //@FXML
    //private DatePicker date_naiss;
    
    private Statement ste;
    private Connection con;


    SanctionService stb = new SanctionService();
    
    ObservableList<String> list = FXCollections.observableArrayList("Alerte","Expulsion","Convocation des Parents");
    @FXML
    private Button Ajouter;
    @FXML
    private TableView<Sanction> AffichageSanction;
    @FXML
    private TableColumn<Sanction, Integer> idTab;
    @FXML
    private TableColumn<Sanction, String> employeTab;
    @FXML
    private TableColumn<Sanction, String> eleveTab;
    @FXML
    private TableColumn<Sanction, String> natureTab;
    @FXML
    private TableColumn<Sanction, String> commentaireTab;
    @FXML
    private TableColumn<Sanction, java.sql.Date> datetimeTab;
    
    private final ObservableList<Sanction> data = FXCollections.observableArrayList();

    @FXML
    private Button Supprimer;
    @FXML
    private TextField recherche;
    
    /*public void Aff(){
                        data.clear(); 
        AffichageSanction = SanctionService.display();
        for (SanctionService sanction : sanction) {
           data.add(Sanction);
        }
        idtab.setCellValueFactory(new PropertyValueFactory<>("id")) ;  
        employetab.setCellValueFactory(new PropertyValueFactory<>("employe"));
        elevetab.setCellValueFactory(new PropertyValueFactory<>("eleve"));
        naturetab.setCellValueFactory(new PropertyValueFactory<>("nature"));  
        commentairetab.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        datetimetab.setCellValueFactory(new PropertyValueFactory<>("datetime"));
        
        AffichageSanction.setItems(data);
         AffichageSanction.setEditable(true);
         tablePromotion.setItems(data); 
         idpromotion.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter())) ; 
         typepromotion.setCellFactory(TextFieldTableCell.forTableColumn()); 
         tauxpromotion.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
         idproduit.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter())) ; 
    }*/
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    /*@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbNature.setItems(list);
        
        //Aff();

        //RechercheAV();

    }
    
    /*public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Sanction> filteredData = new FilteredList<>(data, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(tab_demande -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (tab_demande.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (tab_demande.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(tab_demande.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Sanction> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(AffichageSanction.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		AffichageSanction.setItems(sortedData);
    }
    
    @FXML
    private void Ajouter(ActionEvent event) {
        try {
            int idEmploye= (int) Integer.valueOf(this.tfEmploye.getText());
            int idEleve= (int) Integer.valueOf(this.tfEleve.getText());
            ComboBox nature = (ComboBox) cbNature;
            String natureT = (String) nature.getValue().toString();
            String commentaire = (String) taCommentaire.getText();
            SanctionService ss = new SanctionService();
            Sanction s = new Sanction(idEmploye, idEleve, natureT,commentaire);
            ss.add(s);
            
            FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("AfficheSanction.fxml"));
            try {
                Parent root = loader.load();
                ScreenSanctionController apc = loader.getController();
                apc.setResNom(tfNom);
                apc.setResPrenom(tfPrenom);
                tfNom.getScene().setRoot(root);
                Aff();
                RechercheAV();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
    } 
                    


    @FXML
    private void ButtonSupprimer(ActionEvent event) throws SQLException {
                               		
            tablePromotion.setItems(data);

            ObservableList<Promotion> allDemandes,SingleDemandes ;
            allDemandes=tablePromotion.getItems();
            SingleDemandes=tablePromotion.getSelectionModel().getSelectedItems();
            Promotion A = SingleDemandes.get(0);
            PromotionService STP = new PromotionService();
            STP.Delete(A.getId());
            SingleDemandes.forEach(allDemandes::remove); 
            Aff();
            RechercheAV();

    }



 public void Change_Nom(TableColumn.CellEditEvent bb) throws SQLException{
     Sanction tab_Demandeselected = AffichageSanction.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setNom(bb.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
 }
 
  public void Change_Prenom(TableColumn.CellEditEvent bb) throws SQLException{
     Sanction tab_Demandeselected = AffichageSanction.getSelectionModel().getSelectedItem();
     tab_Demandeselected.SetPrenom(bb.getNewValue().toString());
     stb.updatetab(tab_Demandeselected);
 }
  
   public void Change_Numtel(TableColumn.CellEditEvent bb) throws SQLException{
     Sanction tab_Demandeselected = AffichageSanction.getSelectionModel().getSelectedItem();
     tab_Demandeselected.setNumtel(Integer.parseInt(bb.getNewValue().toString()));
     stb.updatetab(tab_Demandeselected);
 }


    

}*/
