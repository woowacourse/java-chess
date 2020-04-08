package dao;

import java.util.Date;

public class StatusRecordWithRoomName {
	private final String record;
	private final Date gameDate;
	private final String roomName;

	public StatusRecordWithRoomName(final String record, final Date gameDate, final String roomName) {
		this.record = record;
		this.gameDate = gameDate;
		this.roomName = roomName;
	}

	public String getRecord() {
		return record;
	}

	public Date getGameDate() {
		return gameDate;
	}

	public String getRoomName() {
		return roomName;
	}
}
