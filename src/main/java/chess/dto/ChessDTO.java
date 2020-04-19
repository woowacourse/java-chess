package chess.dto;

public class ChessDTO {

	private final String position;
	private final String name;

	public ChessDTO(String position, String name) {
		this.position = position;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "ChessDTO{" +
			"position='" + position + '\'' +
			", name='" + name + '\'' +
			'}';
	}
}
