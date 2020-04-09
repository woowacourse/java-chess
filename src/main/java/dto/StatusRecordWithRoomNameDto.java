package dto;

import java.util.Date;

public class StatusRecordWithRoomNameDto {
	private final String record;
	private final Date gameDate;
	private final String roomName;

	public StatusRecordWithRoomNameDto(final String record, final Date gameDate, final String roomName) {
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
