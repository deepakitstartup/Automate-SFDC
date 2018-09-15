/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adoa;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;
import java.util.Properties;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

/**
 *
 * @author d.g.kumar.sharma
 */
public class ADOA extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        /* BLOCK       ONE */
//Code to set the Path   

        
        /* BLock Two */
        System.out.println("Before The Parent root is invoked  :::");
        FXMLLoader loader = new FXMLLoader();

        // Path to the FXML File
        String fxmlDocPath = "C://Users//d.g.kumar.sharma//workspace//ADOA//src//adoa//FXMLDocument.fxml";

        // String fxmlDocPath = "adoa//FXMLDocument.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
        AnchorPane root = (AnchorPane) loader.load(fxmlStream);

        //Parent root = FXMLLoader.load(getClassetResource("/FXMLDocument.fxml"));
        System.out.println("The Parent root is :::" + root);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        
        
executeAntTask("fetchMetadata.xml","backupMetadata");
        
        
        
      /* ProcessBuilder pb = new ProcessBuilder("CMD", "/C", "SET");
	    Map<String, String> env = pb.environment();
	  //  env.put("ANT_HOME", "");
	    Process p = pb.start();
	    InputStreamReader isr = new InputStreamReader(p.getInputStream());
	    char[] buf = new char[1024];
	    while (!isr.ready()) {
	        ;
	    }
	    while (isr.read(buf) != -1) {
	        System.out.println(buf);
	    }*/
      //cmd.exe /K 
      /*Runtime.getRuntime().exec("cmd /c start  set ANT_HOME=C:\\Users\\d.g.kumar.sharma\\Downloads\\apache-ant_1.9&&"
                 + "set path=%path%;%ant_home%\\bin&& ant");*/
         
	
        
        //launch(args);
    }
    
    /* code to execute the ant script */
    
    
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
     
    
    public void setProperties() throws FileNotFoundException, IOException
    {
        Properties prop = new Properties();
	OutputStream output = null;

        output = new FileOutputStream("config.properties");
        // set the properties value
        prop.setProperty("database", "localhost");
        prop.setProperty("dbuser", "mkyong");
        prop.setProperty("dbpassword", "password");
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

}
