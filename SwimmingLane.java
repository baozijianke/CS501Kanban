import java.util.Arraylist
import java.io.Serializable;

public class SwimmingLane implements Serializable, Comparable<SwimmingLane> {
	private int laneID;
	private String laneTitle;
	private int sequence;
	private ArrayList<Card> innerCards;	

	public SwimmingLane(String title) {
		setID();
		setTitile(title);
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
	
	public boolean setSequence(String sequence) {
		this.laneSquence = squence;
		return true;
	}

	public int getSquence() {
		return this.laneSquence;
	}
	
	public boolean setCardNumber (){
		this.CardNumber = number;
		return true;
	}

	public int getCardNumber() {
		return this.CardNumber;
	}
	
	public boolean addCards(Card card) {
		if (innerCards.contains(card)) {
			return false;
		}
		innerCards.add(card)
		return true
	}
	
	public boolean removeCard(Card card) {
		innerCards.remove(card);
		return true;
	}
}
