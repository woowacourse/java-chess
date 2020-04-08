package chess.domain.dto;

import java.util.List;

public class RoomsDto {
	private List<String> rooms;

	public RoomsDto(List<String> rooms) {
		this.rooms = rooms;
	}

	public List<String> getRooms() {
		return rooms;
	}
}
