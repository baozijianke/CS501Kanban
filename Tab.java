import java.util.ArrayList;

public class Tab {
	public static Tab kanban;
	private int tabID;
	private String tabName;
	private ArrayList<SwimmingLane> innerLanes;
	private int currentLaneID;
	private int currentCardID;
	private int currentUserID;

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

	public SwimmingLane getLanes() {
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
}