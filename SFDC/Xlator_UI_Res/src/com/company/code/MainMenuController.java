package com.company.code;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author d.g.kumar.sharma
 */
public class MainMenuController implements Initializable {
    
    
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

        String respath = "FetchMetaData.fxml";
        InputStream in = ADOAApplication.class.getResourceAsStream(respath);
        if ( in == null )
            throw new Exception("resource not found: " + respath);
        root = (AnchorPane) loader.load(in);
        System.out.println("The Parent root is :::" + root);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        }
        catch(Exception e)
        {
        	 System.out.println("The exception is :::"+e.getMessage());
        }
        
        
        
        
    }
    
}
