package com.company.code;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

/**
 * FXML Controller class
 *
 * @author d.g.kumar.sharma
 */
public class FetchMetadataController implements Initializable {
    
  
private String urlSandbox=null;
private String userName=null;
private String password=null;
@FXML
private ChoiceBox urlFX;
@FXML
private TextField userNameFX;
@FXML
private TextField passwordFX;
@FXML
private TextArea consoleOPFX;
@FXML
private ProgressIndicator fetchAllProgFX;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        urlFX.setItems(FXCollections.observableArrayList(
    "Sandbox", "Production/Development")
);
        urlFX.getSelectionModel().select(0);
        userNameFX.setText("d.g.kumar.sharma@accenture.com.fsc");
        passwordFX.setText("$NoshiHaisha3zdEKDy2Uimfdvku8AY6KUNf4");
        consoleOPFX.setText("Please look into this box to check the progress !!!");
    }   
    @FXML
    public void fetchAll(ActionEvent event) throws IOException,RuntimeException, Exception
    {
        consoleOPFX.setText("Please look into this box to check the progress !!!");
        fetchAllProgFX.setProgress(0);
        String buildFile="/com/company/code/script/build_fetchMetadata.xml";
        String buildTask="backupMetadata";
        try{
        dynamicProperties();
        executeAntTask(buildFile,buildTask);
        }
        catch(FileNotFoundException exp)
        {
            consoleOPFX.setText("Unbale to Find the build file :::"+buildFile+"The detailed erroor is::"+ exp.getMessage());
            throw new Exception(" Unable to find build xml file :::"+exp);
        }
        
        
        catch(RuntimeException exp)
        {
            consoleOPFX.setText("The excption cause  is::"+ exp.getCause() + "\n The stack trace is ::"+ exp.getMessage());
            throw new Exception(" Unable to read the input properties :::"+exp);
        }
        
        catch(Exception exp)
        {
            consoleOPFX.setText("The excption cause  is::"+ exp.getCause() + "\n The stack trace is ::"+ exp.getMessage());
           // throw new RuntimeException(" Unable to read the input properties :::"+exp);
        }
        
        
    }
    
    /**
     * To set the dynamic properties a target specified in the Ant build.xml file
     * 
     * @throws Exception 
     */
    private void dynamicProperties() throws IOException, Exception
    {
        consoleOPFX.setText("");
        urlSandbox=urlFX.getValue().toString();
        userName=userNameFX.getText();
        password=passwordFX.getText();
        System.out.println("URL:::"+urlSandbox+":::::userName:::"+userName+":::::password:::"+password);
        if(urlSandbox.contains("sandbox"))
        {
            urlSandbox="https://test.salesforce.com";
            
        }
        else
        {
            urlSandbox="https://login.salesforce.com";
        }
          if(userName.trim().isEmpty())
        {
                       
            consoleOPFX.setText(consoleOPFX.getText()+"\n User Name Can't be left Blank !!!");
            throw new RuntimeException("\n!!! Unable to execute the ADOA APP !!!\n The error is ::: User Name is Blank");
        
        }
        
        if(password.trim().isEmpty())
        {
                 
            consoleOPFX.setText(consoleOPFX.getText()+"\nPassword Can't be left Blank !!!");
                    throw new RuntimeException("\n!!! Unable to execute the ADOA APP !!!\n The error is ::: Password is Blank");
        
        }
        
             
     consoleOPFX.setText(consoleOPFX.getText()+"\n The environment properties set successfully. Going to Hit ANT and Groovy Environment !!!!");   
        
    }
    
    /**
     * To execute a target specified in the Ant build.xml file
     * 
     * @param buildXmlFileFullPath
     * @param target
     * @throws Exception 
     */
    public  boolean executeAntTask(String buildXmlFileFullPath, String target) throws Exception {
     
       
    	
    	consoleOPFX.setText("");
        boolean success = false;
        DefaultLogger consoleLogger = getConsoleLogger();
 
        // Prepare Ant project
        Project project = new Project();
       InputStream in = FetchMetadataController.class.getResourceAsStream(buildXmlFileFullPath);
        if ( in == null )
            throw new Exception("resource not found: " + buildXmlFileFullPath);
     // File buildFile = new File(buildXmlFileFullPath);
     // File buildFile = new File(in);
    // project.setUserProperty("ant.file", buildFile.getAbsolutePath());
        System.out.println("Pass Check  AAAA!!!!!!");
        
        URL preBuildFileUrl =  FetchMetadataController.class.getResource(buildXmlFileFullPath);
        System.out.println("Pass Check  BBBBBB!!!!!!:::"+preBuildFileUrl);
        
       /* project.setUserProperty("ant.file", preBuildFileUrl.toURI().getPath());
        project.init();
        ProjectHelper helper = ProjectHelper.getProjectHelper();
        p.addReference("ant.projectHelper", helper);
        helper.parse(p, new File(preBuildFileUrl.toURI().getPath()));
        p.executeTarget(p.getDefaultTarget());*/
        try {
        	project.setUserProperty("ant.file", preBuildFileUrl.toURI().getPath());
        	
        	System.out.println("Pass Check  BBBBBB AFter!!!!!!:::"+preBuildFileUrl.toURI().getPath());
      // project.setUserProperty("username", userName);
      // project.setUserProperty("password", password);
      // project.setUserProperty("serverurl", urlSandbox);
       System.out.println("Pass Check  DDDDD!!!!!!");
       
      // setBuildProperties();
       
       System.out.println("Pass Check !!!!!!");
        project.addBuildListener(consoleLogger);
 
        // Capture event for Ant script build start / stop / failure
                project.fireBuildStarted();
            System.out.println("Pass Check  22222!!!!!!");
            project.init();
            ProjectHelper projectHelper = ProjectHelper.getProjectHelper();
            System.out.println("Pass Check 3333!!!!!!");
            project.addReference("ant.projectHelper", projectHelper);
            System.out.println("Pass Check 4444!!!!!!");
          //  projectHelper.parse(project,FetchMetadataController.class.getResourceAsStream(buildXmlFileFullPath) );
            
            projectHelper.parse(project, new File(preBuildFileUrl.toURI().getPath()));
             System.out.println("Pass Check 5555!!!!!!");
             
            // If no target specified then default target will be executed.
            String targetToExecute = (target != null && target.trim().length() > 0) ? target.trim() : project.getDefaultTarget();
            project.executeTarget(targetToExecute);
            project.fireBuildFinished(null);
            success = true;
            consoleOPFX.setText(consoleOPFX.getText()+"\n The data pull request initiated , Need to wait for some minutes [2-30] !!!!");
            System.out.println("Pass Check FFFFF !!!!!!");
            System.out.println(project.getProperties());
            System.out.println(project.getUserProperties());
               
        } 
        
        catch (BuildException buildException) {
            project.fireBuildFinished(buildException);
            consoleOPFX.setText(buildException.getMessage().toString());
            throw new RuntimeException("\n!!! Unable to restart the ADOA APP !!!\n The error is :::"+buildException.getMessage(), buildException);
        }
        finally
                {
                    consoleOPFX.setText("The metadata pulled successfully !!!!!");
                }
         
        return success;
    }
    
    
     /**
     * Logger to log output generated while executing ant script in console
     * 
     * @return
     */
    private static DefaultLogger getConsoleLogger() {
        DefaultLogger consoleLogger = new DefaultLogger();
        consoleLogger.setErrorPrintStream(System.err);
        consoleLogger.setOutputPrintStream(System.out);
        consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
         
        return consoleLogger;
    }
    
   private void setBuildProperties()
   {
	   FileOutputStream out;
	   FileInputStream in;
	   Properties props = new Properties();
	try {
		out = new FileOutputStream("/build.properties");
		in = new FileInputStream("/build.properties");
	  
		props.load(in);
		in.close();
		props.setProperty("sf.username", userName);
	       props.setProperty("sf.password", password);
	       props.setProperty("sf.serverurl", urlSandbox);
	       
	       props.store(out, null);
	       out.close();
	} 
	 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
		
	
       
	   
   }
    
    
}
