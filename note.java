package com.gn.module.note;

import com.Olympians.Entite.Matiers;
import com.Olympians.Entite.Notes;
import com.Olympians.Service.ServiceMatiers;
import com.Olympians.Service.ServiceNotes;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import com.itextpdf.text.Document;
import java.io.FileOutputStream;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
public class note implements Initializable{

    
           
    @FXML private TableView<Notes> tableView;
        @FXML private TableColumn<String, Notes> c0;
    @FXML private TableColumn<String, Notes> c1;
    @FXML private TableColumn<String, Notes> c2;
    @FXML private TableColumn<String, Notes> c3;
    @FXML private TableColumn<String, Matiers> c4;
    @FXML private TableColumn<String, Notes> c5;
    @FXML private TableColumn<String, Notes> c6;
    @FXML private TableColumn<String, Notes> c7;
    @FXML private TextField id;
    @FXML private TextField formule;
    @FXML private TextField classe;
    @FXML private TextField matiere;
    @FXML private TextField etudent;
    @FXML private TextField prof;
    @FXML private TextField note;
    @FXML private TextField remarque;
    @FXML private TextField search;
    
    


 private static Font basicFont = new Font(Font.FontFamily.TIMES_ROMAN, 26,
            Font.BOLD);

    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
               ServiceNotes srvnote = new ServiceNotes();
                ObservableList<Notes> cls = FXCollections.observableArrayList();
                List<Notes> list = srvnote.readAll();

                   for (Notes aux : list)
                {
                  cls.add(new Notes(aux.getId(),aux.getNote(),aux.getFormule(),aux.getRemarque(),aux.getId_class(), aux.getId_matiere(),aux.getId_etudent(), aux.getId_prof()));
                }

      c0.setCellValueFactory(new PropertyValueFactory<>("id"));
        c1.setCellValueFactory(new PropertyValueFactory<>("formule"));
     c2.setCellValueFactory(new PropertyValueFactory<>("id_class"));
        c3.setCellValueFactory(new PropertyValueFactory<>("id_matiere"));
     c4.setCellValueFactory(new PropertyValueFactory<>("id_etudent"));
        c5.setCellValueFactory(new PropertyValueFactory<>("id_prof"));
     c6.setCellValueFactory(new PropertyValueFactory<>("note"));
        c7.setCellValueFactory(new PropertyValueFactory<>("remarque"));
       tableView.setItems(cls);
        }catch (SQLException ex) {
        
                    System.out.println(ex);
                 }

        
    }
    
    
   
    
    @FXML
    public void Update(){ 
    
         ServiceNotes tmp = new ServiceNotes(); 
        try {
                 
                          
                String Formule = (String) formule.getText();
                int Classid= (int) Integer.valueOf(classe.getText());
                int Matereid= (int) Integer.valueOf(matiere.getText());
                int Etudentid = (int) Integer.valueOf(etudent.getText());
                int Profid = (int) Integer.valueOf(prof.getText());
                double Note = Double.valueOf(note.getText());
                String Remarque = (String) remarque.getText();
              
                 ServiceNotes ser = new ServiceNotes();
                  
                 Notes tmp2=tableView.getSelectionModel().getSelectedItem();
                    Notes N1 = new Notes(tmp2.getId(),Note,Formule,Remarque,Classid, Matereid, Etudentid, Profid);          
                 ser.update(N1,N1.getId());
                    Afficher();
                   
                 }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
        
        
    }
    
    @FXML
    public void Delete(){ 
        ServiceNotes tmp = new ServiceNotes(); 
        try {  
                 ServiceNotes ser = new ServiceNotes();
                 Notes tmp2 = tableView.getSelectionModel().getSelectedItem();
                 System.out.println(tmp2);
                   Notes N1 =  new Notes(tmp2.getId(),tmp2.getNote(),tmp2.getFormule(),tmp2.getRemarque(),tmp2.getId_class(), tmp2.getId_matiere(),tmp2.getId_etudent(), tmp2.getId_prof());
                   ser.delete(N1.getId());
                   Afficher();
             
                 }
                 catch (SQLException ex) {
               
                    System.out.println(ex);
                 }
        
        
    }
    
     @FXML
    public void Add(){ 
        
        
              
        try {
                 
                 String Formule = (String) formule.getText();
                int Classid= (int) Integer.valueOf(classe.getText());
                int Matereid= (int) Integer.valueOf(matiere.getText());
                int Etudentid = (int) Integer.valueOf(etudent.getText());
                int Profid = (int) Integer.valueOf(prof.getText());
                double Note = Double.valueOf(note.getText());
                String Remarque = (String) remarque.getText();
           
                
                ServiceNotes srvnote = new ServiceNotes();
                
                Notes N = new Notes(Note,Formule,Remarque,Classid, Matereid, Etudentid, Profid);
                                    
                   srvnote.ajouter(N);
                   Afficher(Classid);
              
                 }
                 catch (SQLException ex) {
  
                    System.out.println(ex);
                 }
    
    }

    
      void Afficher(int c){
          try {
                 
             
               
                ServiceNotes srvnote = new ServiceNotes();
                ObservableList<Notes> cls = FXCollections.observableArrayList();
                List<Notes> list = srvnote.SearchByClass(c);

                   for (Notes aux : list)
                {
                  cls.add(new Notes(aux.getId(),aux.getNote(),aux.getFormule(),aux.getRemarque(),aux.getId_class(), aux.getId_matiere(),aux.getId_etudent(), aux.getId_prof()));
                }
                     tableView.setItems(cls);
              
                 }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
          
          
          
        
  }
      
         
      void Afficher(){
          try {
                 
            
        ServiceNotes srvnote = new ServiceNotes();
                ObservableList<Notes> cls = FXCollections.observableArrayList();
                List<Notes> list = srvnote.readAll();

                   for (Notes aux : list)
                {
                  cls.add(new Notes(aux.getId(),aux.getNote(),aux.getFormule(),aux.getRemarque(),aux.getId_class(), aux.getId_matiere(),aux.getId_etudent(), aux.getId_prof()));
                }
       tableView.setItems(cls);

          }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
          
          
          
        
  }
      
          @FXML
    public void PDF(){ 
    
          try {   
              
              ServiceNotes srvnote = new ServiceNotes();
                        String classid = (String) classe.getText();
                         List<Notes> list = srvnote.SearchByClass(Integer.valueOf(classid));
                        Document document = new Document();
                        PdfWriter.getInstance(document, new FileOutputStream("C:/Users/Bsm/Documents/output.pdf"));
                        document.open();
                        addMetaData(document);
                        addTitlePage(document, list);
                        document.close();
                       Runtime.getRuntime().exec("explorer.exe C:/Users/Bsm/Documents/output.pdf");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

        
        

    
    
    }
    
    
    
    
        private static void addTitlePage(Document document, List<Notes> list)
            throws DocumentException {
        Paragraph preface = new Paragraph();
        // We add one empty line
        addEmptyLine(preface, 1);
        // Lets write a big header
        Paragraph title = new Paragraph("3almni Platforme", basicFont);
        title.setAlignment(Element.ALIGN_CENTER);
        preface.add(title);
        addEmptyLine(preface, 1);
        preface.add(new Paragraph("List Notes", catFont));

        Paragraph prof = new Paragraph("Prof : Bessem Bousselmi", smallBold);
        prof.setAlignment(Element.ALIGN_RIGHT);
        preface.add(prof);

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Date :", redFont));

        Paragraph Info = new Paragraph("Programmation Stm32", smallBold);
        Info.setAlignment(Element.ALIGN_CENTER);
        preface.add(Info);

        addEmptyLine(preface, 3);

        PdfPTable table = new PdfPTable(3);

        PdfPCell c1 = new PdfPCell(new Phrase("Etudent"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Note"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Remarque"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);

        list.stream().forEach((N) -> {
            System.out.println(N.toString());
            table.addCell(String.valueOf(N.getId_etudent()));
            table.addCell(String.valueOf(N.getNote()));
            table.addCell(N.getRemarque());
        });

        preface.add(table);

        document.add(preface);

    }
     
     
     
     
   
     
     
     
     private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
     }
     
     
     
     
     
     
    private static void addMetaData(Document document) {
        document.addTitle("Note ");
        document.addSubject("Note");
        document.addKeywords("Note, PDF");
        document.addAuthor("3almni");
        document.addCreator("3almni");
    }


     

}
