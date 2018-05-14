import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

public class Card implements Serializable, Comparable<Card> {
	private int cardID;
	private String cardTitle;				//required
	private String cardDescription;			//optianal
	private int cardPriority;				//optianal
	private ArrayList<User> relatedUser;	//optianal
	private Date startDate;					//optianal
	private Date dueDate;					//optianal

	public Card(String title) {
		setID();
		setTitle(title);
	}

	public boolean setID() {
		this.cardID = Tab.kanban.getNewCardID();
		return true;
	}

	public int getID() {
		return this.cardID;
	}

	public boolean setTitle(String title) {
		this.cardTitle = title;
		return true;
	}

	public String getTitle() {
		return this.cardTitle;
	}

	public boolean setDescription(String text) {
		this.cardDescription = text;
		return true;
	}

	public String getDescription() {
		return this.cardDescription;
	}

	public boolean setCardPriority(int prio) {
		if (prio < 1 || prio >3) {
			return false;
		}
		this.cardPriority = prio;
		return true;
	}

	public int getCardPriority() {
		return this.cardPriority;
	}

	public boolean assignTo(User user) {
		if (relatedUser.contains(user)) {
			return false;
		}
		relatedUser.add(user);
		return true;
	}

	public ArrayList<User> getUserList() {
		return this.relatedUser;
	}

	public boolean removeUserByID(int id) {
		for (User user : relatedUser) {
			if (user.getID() == id) {
				relatedUser.remove(user);
				return true;
			}
		}
		return false;
	}

	public boolean setStartDate(Date date) {
		this.startDate = date;
		return true;
	}

	public boolean setDueDate(Date date) {
		this.dueDate = date;
		return true;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public Date getDueDate() {
		return this.dueDate;
	}

	@Override
	public int compareTo(Object o) {
		Card card = (Card)o;
		if (this.Date.before(card.Date)) {
			return 1;
		}
		else {
			return -1;
		}
	}

}