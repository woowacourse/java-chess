package dao;

public class Announcement {
	private final int id;
	private final String announcement;
	private final int roomId;

	public Announcement(final int id, final String announcement, final int roomId) {
		this.id = id;
		this.announcement = announcement;
		this.roomId = roomId;
	}

	public int getId() {
		return id;
	}

	public String getMessage() {
		return announcement;
	}

	public int getRoomId() {
		return roomId;
	}
}
