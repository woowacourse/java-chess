package dao;

import java.util.Date;

public class StatusRecord {
	private final int id;
	private final String record;
	private final Date gameDate;
	private final int roomId;

	public StatusRecord(final int id, final String record, final Date gameDate, final int roomId) {
		this.id = id;
		this.record = record;
		this.gameDate = gameDate;
		this.roomId = roomId;
	}

	public int getId() {
		return id;
	}

	public String getRecord() {
		return record;
	}

	public Date getGameDate() {
		return gameDate;
	}

	public int getRoomId() {
		return roomId;
	}

	@Override
	public String toString() {
		return "StatusRecord{" +
				"id=" + id +
				", record='" + record + '\'' +
				", gameDate=" + gameDate +
				", roomId=" + roomId +
				'}';
	}
}
