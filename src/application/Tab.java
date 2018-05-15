package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javafx.scene.layout.Pane;

public class Tab implements Serializable {
	public static Tab kanban = new Tab("Kanban");
	public static final String FILE_NAME = "KanbanTab";

	private int tabID;
	private String tabName;
	private ArrayList<SwimmingLane> innerLanes = new  ArrayList<SwimmingLane>();
	private ArrayList<Card> cards = new ArrayList<Card>();
	private Map<Integer, Pane> cardIdPaneMap= new HashMap<Integer, Pane>();
	private int currentLaneID;
	private int currentCardID;
	private int currentUserID;

	public void addIdPaneMap(Integer id, Pane pane) {
		cardIdPaneMap.put(id, pane);
	}
	
	public Pane getPaneById(Integer id) {
		return cardIdPaneMap.get(id);
	}
	
	public void addCard(Card card) {
		cards.add(card);
	}
	
	public Card getCard(int id) {
		for(Card card: cards) {
			if(card.getID() == id) {
				return card;
			}
		}
		return null;
	}
	
	public Tab(String name) {
		setName(name);
		currentUserID = 0;
		currentLaneID = 0;
		currentCardID = 0;
	}

	public boolean setId(int id) {
		this.tabID = id;
		return true;
	}

	public int getId() {
		return this.tabID;
	}

	public boolean setName(String name) {
		this.tabName = name;
		return true;
	}

	public String getName() {
		return this.tabName;
	}

	public boolean addLane(SwimmingLane lane) {
		if (innerLanes.contains(lane)) {
			return false;
		}
		innerLanes.add(lane);
		return true;
	}

	public ArrayList<SwimmingLane> getLanes() {
		return innerLanes;
	}

	public int getNewLaneID() {
		currentLaneID++;
		return currentLaneID;
	}

	public int getNewCardID() {
		currentCardID++;
		return currentCardID;
	}

	public int getNewUserID() {
		currentUserID++;
		return currentUserID;
	}

	public static boolean save() {
		try {
			FileOutputStream fop = new FileOutputStream(FILE_NAME);
			ObjectOutputStream oos = new ObjectOutputStream(fop);
			oos.writeObject(kanban);
			oos.close();	
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public static boolean readFile() {
		try {
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			kanban = (Tab)ois.readObject();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}