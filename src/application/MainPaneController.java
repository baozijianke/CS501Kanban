package application;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainPaneController {
	public MainPaneController() {
		// TODO Auto-generated constructor stub
	}
	@FXML
	private Button btnSubmit;
	@FXML
	private Button btnCancel;
	@FXML
	private Button btnRemove;
	@FXML
	private Pane kanbanPane;
	@FXML
	private Button btnAddTodo;
	@FXML
	private Button btnAddDoing;
	@FXML
	private Button btnAddDone;
	
	@FXML
	private TextArea descriptionArea;
	@FXML
	private TextField cardTitle;
	@FXML
	private DatePicker startDate;
	@FXML
	private DatePicker dueDate;
	@FXML
	private TextField estimateTime;
	@FXML
	private TextField personInCharge;
	@FXML
	private MenuButton priorityMenu;

	@FXML 
	private VBox todoPane;
	@FXML 
	private VBox doingPane;
	@FXML 
	private VBox donePane;

	private static int curLaneId;
	private int curCardId;
	
	@FXML
	protected void handleCancelBtnAction(ActionEvent event) {
		disableDetialsPane();
	}
	@FXML
	protected void handleSubmitBtnAction(ActionEvent event) {
		String description = descriptionArea.getText();
		String title = cardTitle.getText();
		LocalDate sDate = startDate.getValue();
		LocalDate dDate = dueDate.getValue();
		String user = personInCharge.getText();
		String priority = priorityMenu.getText();
		
		String color = null;
		int priorityNum = 0;
		if (priority.equals("High")) {
			color = "#ff8484";
			priorityNum = 1;
		}else if (priority.equals("Mid")) {
			color = " #fcff51";
			priorityNum = 2;
		}else if (priority.equals("Low")) {
			color = " #92f47f";
			priorityNum = 3;
		}
		
		
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainApp.class.getResource("cardUI.fxml"));
		Pane cardPane = null;
		try {
			cardPane = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CardController cController = (CardController)loader.getController();
		
		
		Card card = addCard(description, title, sDate, dDate, user, priorityNum, curLaneId, cardPane);
		
		cController.setCardId(card.getID());
		cController.setLaneId(curLaneId);
		
		Label taskName = new Label();
		Label personInCharge = new Label();
		taskName.setText(card.getID() + ":" + title);
		personInCharge.setText("by "+user);
		taskName.setLayoutX(14);
		taskName.setLayoutY(14);
		personInCharge.setLayoutX(14);
		personInCharge.setLayoutY(51);
		
		cardPane.getChildren().addAll(taskName,personInCharge);
		
		cardPane.setStyle("-fx-margin:15,15,0,15;"
				+ "-fx-background-color:" + color);
		
		if(curLaneId == 0) {
			todoPane.getChildren().add(cardPane);
			todoPane.setMargin(cardPane, new Insets(15, 15, 0, 15));
		}
		if(curLaneId == 1) {
			doingPane.getChildren().add(cardPane);
			doingPane.setMargin(cardPane, new Insets(15, 15, 0, 15));
		}
		if(curLaneId == 2) {
			donePane.getChildren().add(cardPane);
			donePane.setMargin(cardPane, new Insets(15, 15, 0, 15));
		}
		
		
	}
	@FXML
	protected void handleRemoveBtnAction(ActionEvent event) {
		Pane pane = Tab.kanban.getPaneById(curCardId);
		if(curLaneId == 0) {
			todoPane.getChildren().remove(pane);
		}
		if(curLaneId == 1) {
			doingPane.getChildren().remove(pane);
		}
		if(curLaneId == 2) {
			donePane.getChildren().remove(pane);
		}
	}
	@FXML
	protected void handleAddTodoBtnAction(ActionEvent event) {
		resetDetialsPane();
		curLaneId = 0;
		
	}
	@FXML
	protected void handleAddDoingBtnAction(ActionEvent event) {
		resetDetialsPane();
		curLaneId = 1;
	}
	@FXML
	protected void handleAddDoneBtnAction(ActionEvent event) {
		resetDetialsPane();
		curLaneId = 2;
	}
	
	@FXML
	protected void handleHighPriority(ActionEvent event) {
		priorityMenu.setText("High");
	}
	
	@FXML
	protected void handleMidPriority(ActionEvent event) {
		priorityMenu.setText("Mid");
	}
	
	@FXML
	protected void handleLowPriority(ActionEvent event) {
		priorityMenu.setText("Low");
	}
	
	
	
	protected void resetDetialsPane() {
		descriptionArea.clear();
		cardTitle.clear();
		
		LocalDate now = LocalDate.now();  
		startDate.setValue(now);
		dueDate.setValue(now);
		estimateTime.setDisable(true);
		personInCharge.clear();
		
		descriptionArea.setDisable(false);
		cardTitle.setDisable(false);
		startDate.setDisable(false);
		dueDate.setDisable(false);
		personInCharge.setDisable(false);
		priorityMenu.setDisable(false);
		
		btnSubmit.setDisable(false);
		btnCancel.setDisable(false);
		btnRemove.setDisable(true);
	}
	
	protected void disableDetialsPane() {
		descriptionArea.clear();
		cardTitle.clear();
		LocalDate now = LocalDate.now();  
		startDate.setValue(now);
		dueDate.setValue(now);
		personInCharge.clear();
		

		descriptionArea.setDisable(true);
		cardTitle.setDisable(true);
		startDate.setDisable(true);
		dueDate.setDisable(true);
		estimateTime.setDisable(true);
		personInCharge.setDisable(true);
		
		btnSubmit.setDisable(true);
		btnCancel.setDisable(true);
		btnRemove.setDisable(true);
		
	}
	
	public Card addCard(String desc, String title, LocalDate sDate, LocalDate dDate, String user, int priority, int laneNumber, Pane pane) {
		Card card = new Card();
		card.setID();
		card.setDescription(desc);
		card.setTitle(title);
		card.setStartDate(java.sql.Date.valueOf(sDate));
		card.setDueDate(java.sql.Date.valueOf(dDate));
		card.assignTo(user);
		card.setCardPriority(priority);
		
		Tab.kanban.getLanes().get(laneNumber).addCards(card);
		Tab.kanban.addCard(card);
		Tab.kanban.addIdPaneMap(card.getID(), pane);
		
		return card;
	}
	
	public void fillDetails(int id) {
		
		Card curCard = Tab.kanban.getCard(id);
		curCardId = id;
		descriptionArea.setText(curCard.getDescription());
		cardTitle.setText(curCard.getTitle());
		Date d = curCard.getStartDate();
		startDate.setValue(LocalDate.of(d.getYear()+1900, d.getMonth()+1, d.getDate()));
		d = curCard.getDueDate();
		dueDate.setValue(LocalDate.of(d.getYear()+1900, d.getMonth()+1, d.getDate()));
		
		Map<TimeUnit,Long> duration = computeDiff(curCard.getStartDate(),curCard.getDueDate());

		estimateTime.setText((duration.get(TimeUnit.DAYS)+1)+"Days");
		personInCharge.setText(curCard.getUserList().get(0));
		
		int priority = curCard.getCardPriority();
		if(priority == 1) {
			priorityMenu.setText("High");
		}
		if(priority == 2) {
			priorityMenu.setText("Mid");
		}
		if(priority == 3) {
			priorityMenu.setText("Low");
		}
		
		btnSubmit.setDisable(true);
		btnCancel.setDisable(true);
		btnRemove.setDisable(false);
	}
	
	public static Map<TimeUnit,Long> computeDiff(Date date1, Date date2) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
	    Collections.reverse(units);
	    Map<TimeUnit,Long> result = new LinkedHashMap<TimeUnit,Long>();
	    long milliesRest = diffInMillies;
	    for ( TimeUnit unit : units ) {
	        long diff = unit.convert(milliesRest,TimeUnit.MILLISECONDS);
	        long diffInMilliesForUnit = unit.toMillis(diff);
	        milliesRest = milliesRest - diffInMilliesForUnit;
	        result.put(unit,diff);
	    }
	    return result;
	}
	
	public static void setCurLaneId(int id) {
		curLaneId = id;
	}
	
}
