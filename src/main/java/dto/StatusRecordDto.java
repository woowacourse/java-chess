package dto;

import java.util.Date;

public class StatusRecordDto {
	private final int id;
	private final String record;
	private final Date gameDate;
	private final String roomName;

	public StatusRecordDto(final int id, final String record, final Date gameDate, final String roomName) {
		this.id = id;
		this.record = record;
		this.gameDate = gameDate;
		this.roomName = roomName;
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

	public String getRoomName() {
		return roomName;
	}
}
