package com.company.code;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Properties;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
public class ADOAApplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		System.out.println("Before The Parent root is invoked  :::");
		FXMLLoader loader = new FXMLLoader();
		System.out.println(System.getProperty("user.dir"));
		// Path to the FXML File
		String respath = "MainMenu.fxml";
		InputStream in = ADOAApplication.class.getResourceAsStream(respath);
		if ( in == null )
			throw new Exception("resource not found: " + respath);
		// FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);
		AnchorPane root = (AnchorPane) loader.load(in);
		System.out.println("The Parent root is :::" + root);
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException {

		//executeAntTask("fetchMetadata.xml","backupMetadata");
		launch(args);
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


}
