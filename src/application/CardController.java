package application;

import java.io.IOException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class CardController {
	private int laneId;
	private int cardId;
	@FXML
	private Pane card;

	@FXML
	protected void handleOnMouseClicked(MouseEvent e) {
		MainApp.mainController.fillDetails(cardId);
		MainApp.mainController.setCurLaneId(laneId);
		
		MouseButton button = e.getButton();
        if(button==MouseButton.PRIMARY){
            System.out.println("PRIMARY button clicked");
        }else if(button==MouseButton.SECONDARY){
        	System.out.println("SECONDARY button clicked");
        }else if(button==MouseButton.MIDDLE){
        	System.out.println("MIDDLE button clicked");
        }
        
        if(button==MouseButton.SECONDARY){
        	if(laneId == 0) {
        		ContextMenu contextMenu = new ContextMenu();
                MenuItem item1 = new MenuItem("Move to Doing");
                MenuItem item2 = new MenuItem("Move to Done");
                item1.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override public void handle(ActionEvent e)
                    {
                    	MainApp.mainController.moveCard(cardId, 1);
                    	laneId = 1;
                    }
                });
                item2.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override public void handle(ActionEvent e)
                    {
                    	MainApp.mainController.moveCard(cardId, 2);
                    	laneId = 2;
                    }
                });
                
                contextMenu.getItems().addAll(item1, item2);
                contextMenu.show(card, e.getScreenX(), e.getScreenY());
        	}
        	if(laneId == 1) {
        		ContextMenu contextMenu = new ContextMenu();
                MenuItem item1 = new MenuItem("Move to Todo");
                MenuItem item2 = new MenuItem("Move to Done");
                item1.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override public void handle(ActionEvent e)
                    {
                    	MainApp.mainController.moveCard(cardId, 0);
                    	laneId = 0;
                    }
                });
                item2.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override public void handle(ActionEvent e)
                    {
                    	MainApp.mainController.moveCard(cardId, 2);
                    	laneId = 2;
                    }
                });
                
                contextMenu.getItems().addAll(item1, item2);
                contextMenu.show(card, e.getScreenX(), e.getScreenY());
        	}
        	if(laneId == 2) {
        		ContextMenu contextMenu = new ContextMenu();
                MenuItem item1 = new MenuItem("Move to Todo");
                MenuItem item2 = new MenuItem("Move to Doing");
                item1.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override public void handle(ActionEvent e)
                    {
                    	MainApp.mainController.moveCard(cardId, 0);
                    	laneId = 0;
                    }
                });
                item2.setOnAction(new EventHandler<ActionEvent>()
                {
                    @Override public void handle(ActionEvent e)
                    {
                    	MainApp.mainController.moveCard(cardId, 1);
                    	laneId = 1;
                    }
                });
                
                contextMenu.getItems().addAll(item1, item2);
                contextMenu.show(card, e.getScreenX(), e.getScreenY());
        	}
        	
        }
        
	}
	
	public void setCardId(int id) {
		cardId = id;
	}
	
	public void setLaneId(int id) {
		laneId = id;
	}
}
