package edu.westga.cs1302.inventory_management;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/** Starting point for the application
 * 
 * @author CS 1302
 * @version Fall 2022
 */
public class Main extends Application {
	private static final String GUI_RESOURCE = "view/MainWindow.fxml";

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent parent = FXMLLoader.load(getClass().getResource(GUI_RESOURCE));
		Scene scene = new Scene(parent);
		primaryStage.setTitle("Inventory Management System");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	/** Launching point for the application.
	 * 
	 * @precondition none
	 * @postcondition none
	 * 
	 * @param args command line options
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
