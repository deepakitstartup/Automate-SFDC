/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adoa;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author d.g.kumar.sharma
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
     @FXML
    public void displayAll(ActionEvent event)
    {   
        Parent root;
        System.out.println("Inside displayAll");
        try{
        FXMLLoader loader = new FXMLLoader();

        // Path to the FXML File

        String fxmlDocPath = "C://Users//d.g.kumar.sharma//workspace//ADOA//src//adoa//MetadataAll.fxml";
        
       // String fxmlDocPath = "adoa//FXMLDocument.fxml";

        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
         root = (AnchorPane) loader.load(fxmlStream);


        //Parent root = FXMLLoader.load(getClassetResource("/FXMLDocument.fxml"));
        System.out.println("The Parent root is :::"+root);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        }
        catch(Exception e)
        {
            
        }
        
        
        
        
    }
    
}
