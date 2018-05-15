package application;

import java.io.Serializable;
import java.util.ArrayList;

public class SwimmingLane implements Serializable {
	private int laneID;
	private String laneTitle;
	private int laneSquence;
	private ArrayList<Card> innerCards;	

	public SwimmingLane(String title) {
		innerCards = new  ArrayList<Card>();
		setID();
		setTitle(title);
	}
	
	public boolean setID() {
		this.laneID = Tab.kanban.getNewLaneID();
		return true;
	}

	public int getID() {
		return this.laneID;
	}
	
	public boolean setTitle(String title) {
		this.laneTitle = title;
		return true;
	}

	public String getTitle() {
		return this.laneTitle;
	}
	
	public boolean setSequence(int sequence) {
		this.laneSquence = sequence;
		return true;
	}

	public int getSquence() {
		return this.laneSquence;
	}

	public int getCardNumber() {
		return this.innerCards.size();
	}
	
	public boolean addCards(Card card) {
		if (innerCards.contains(card)) {
			return false;
		}
		innerCards.add(card);
		return true;
	}
	
	public boolean removeCard(Card card) {
		innerCards.remove(card);
		return true;
	}
}
