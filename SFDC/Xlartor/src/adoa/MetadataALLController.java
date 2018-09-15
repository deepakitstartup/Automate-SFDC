/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adoa;

import static adoa.ADOA.executeAntTask;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
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
public class MetadataALLController implements Initializable {
    
   private String pollWaitMills=null;
 private    String maxPoll=null;
    private    String batchSize=null;
    private    String antlibVer=null;
private String url=null;
private String userName=null;
private String password=null;
@FXML
private ChoiceBox urlFX;
@FXML
private TextField userNameFX;
@FXML
private TextField passwordFX;
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
    }   
    @FXML
    public void fetchAll(ActionEvent event) throws IOException
    {
        
        
         /*Runtime.getRuntime().exec("cmd /c start cmd.exe /K  set ANT_HOME=C:\\Users\\d.g.kumar.sharma\\Downloads\\apache-ant &&"
                 + "set PATH=%PATH%;%ANT_HOME%\\bin && ant");
         
       // ProcessBuilder pb = new ProcessBuilder("cmd /c start cmd.exe /K \"dir && ping localhost\"");
	   // Map<String, String> env = pb.environment();
	  //  env.put("ANT_HOME", "");
	    //Process p = pb.start();
	    //InputStreamReader isr = new InputStreamReader(p.getInputStream());
	    //char[] buf = new char[1024];
	    //while (!isr.ready()) {
	      //  ;
	    //}
	    //while (isr.read(buf) != -1) {
	      //  System.out.println(buf);
	    //}
       
        //TODO
        //1. Set path to have ant
        //2. Set path to have groovy
        //defaultProperties();*/
        dynamicProperties();
        executeAntTask("fetchMetadata.xml","backupMetadata");
        
        
    }
  /*  private void defaultProperties()
    {
       
        Defaults
sf.pollWaitMillis = 100000
sf.maxPoll = 2000
sf.batchSize = 20

# The version of the antlib jar in lib
sf.antlib.version = 39.0*
        pollWaitMills="100000";
        maxPoll="2000";
        batchSize="20";
        antlibVer="39.0";
    }*/
    private void dynamicProperties() throws FileNotFoundException, IOException
    {
        //url=urlFX.getSelectionModel().getSelectedItem().toString();
        url=urlFX.getValue().toString();
        userName=userNameFX.getText();
        password=passwordFX.getText();
        System.out.println("URL:::"+url+"userName:::"+userName+"password:::"+password);
        if(url.contains("sandbox"))
        {
            url="https://test.salesforce.com";
            
        }
        else
        {
            url="https://login.salesforce.com";
        }
        
        Properties prop = new Properties();
	OutputStream output = null;

        output = new FileOutputStream("build.properties");
        // set the properties value
        prop.setProperty("sf.username", userName);
        prop.setProperty("sf.password", password);
        prop.setProperty("sf.serverurl", url);
        // save properties to project root folder
        prop.store(output, null);
        if (output != null) {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        
        
    }
    
    /**
     * To execute a target specified in the Ant build.xml file
     * 
     * @param buildXmlFileFullPath
     * @param target
     */
    public static boolean executeAntTask(String buildXmlFileFullPath, String target) {
          boolean success = false;
        DefaultLogger consoleLogger = getConsoleLogger();
 
        // Prepare Ant project
        Project project = new Project();
        File buildFile = new File(buildXmlFileFullPath);
       project.setUserProperty("ant.file", buildFile.getAbsolutePath());
        project.addBuildListener(consoleLogger);
 
        // Capture event for Ant script build start / stop / failure
        try {
            project.fireBuildStarted();
            project.init();
            ProjectHelper projectHelper = ProjectHelper.getProjectHelper();
            project.addReference("ant.projectHelper", projectHelper);
            projectHelper.parse(project, buildFile);
             
            // If no target specified then default target will be executed.
            String targetToExecute = (target != null && target.trim().length() > 0) ? target.trim() : project.getDefaultTarget();
            project.executeTarget(targetToExecute);
            project.fireBuildFinished(null);
            success = true;
        } catch (BuildException buildException) {
            project.fireBuildFinished(buildException);
            throw new RuntimeException("!!! Unable to restart the IEHS App !!!", buildException);
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
    
        
    
    
}
