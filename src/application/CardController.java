package application;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class CardController {
	private int laneId;
	private int cardId;
	@FXML
	private Pane card;

	@FXML
	protected void handleOnMouseClicked() {
		MainApp.mainController.fillDetails(cardId);
		MainApp.mainController.setCurLaneId(laneId);
	}
	
	public void setCardId(int id) {
		cardId = id;
	}
	
	public void setLaneId(int id) {
		laneId = id;
	}
}
