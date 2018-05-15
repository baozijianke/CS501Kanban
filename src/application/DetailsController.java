package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class DetailsController {
	private static Pane newDetailsPane;

	
	
	public DetailsController() {
		// TODO Auto-generated constructor stub
	}
	
	public static Pane addDetails() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("detailsUI.fxml"));
		
		try {
			newDetailsPane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newDetailsPane;
	}

}
