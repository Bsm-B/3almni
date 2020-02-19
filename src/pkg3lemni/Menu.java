/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3lemni.GUI;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pkg3lemni.entity.Sanction;
import pkg3lemni.service.SanctionService;

/**
 *
 * @author wajih
 */
public class Menu extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
       //FXMLLoader Loader =  new FXMLLoader(getClass().getResource("AjouterMatiere.fxml"));
       FXMLLoader Loader =  new FXMLLoader(getClass().getResource("Menu.fxml"));
       Parent root= Loader.load();
        Scene scene = new Scene(root);
       
        stage.setTitle("Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
