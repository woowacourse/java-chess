package chess.domain.dto;

public class RoomDto {
	private String name;

	public RoomDto(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
