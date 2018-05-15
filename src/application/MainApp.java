package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class MainApp extends Application {
	private Stage primaryStage;
	private Pane mainLayout;
	public static MainPaneController mainController;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Test KanbanUI");
		initKanban();
		showKanbanUI();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	private void showKanbanUI() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("kanbanUI.fxml"));
		try {
			mainLayout = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		mainController = (MainPaneController)loader.getController();

		
		Scene scene = new Scene(mainLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void initKanban() {
		SwimmingLane laneTodo = new SwimmingLane("Todo");
		SwimmingLane laneDoing = new SwimmingLane("Doing");
		SwimmingLane laneDone = new SwimmingLane("Done");
		
		Tab.kanban.addLane(laneTodo);
		Tab.kanban.addLane(laneDoing);
		Tab.kanban.addLane(laneDone);
	}
}
